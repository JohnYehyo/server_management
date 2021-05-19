package com.johnyehyo.servermanage.service.impl;

import com.johnyehyo.servermanage.core.enums.CommondEnum;
import com.johnyehyo.servermanage.core.param.FileParam;
import com.johnyehyo.servermanage.core.util.FileAction;
import com.johnyehyo.servermanage.core.util.LogUtils;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.service.TomcatService;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @description: tomcat管理
 * @author: JohnYehyo
 * @create: 2021-05-10 17:31:36
 */
@Service
@PropertySource("classpath:application.yml")
public class TomcatServiceImpl implements TomcatService {

    @Value("${tomcatfile.savePath}")
    private String filePath;

    @Autowired
    private FileAction fileAction;

    /**
     * tomcat升级
     *
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    @Override
    public ResponseVo upgrade(FileParam fileParam) {

        String tomcatDir = fileParam.getTomcatDir();
        String savePath = tomcatDir + filePath;

        boolean result = download(fileParam, savePath);

        if (!result) {
            return ResponseVo.error();
        }

        String stopCommand;
        String delCommand;
        String startCommand;
        boolean isLinux = isLinux();
        if (isLinux) {
            stopCommand = "cd " + tomcatDir + "bin" + "&& ./shutdown.sh";
            delCommand = "cd " + tomcatDir + "webapps" + "&&rm -rf ROOT";
            startCommand = "cd " + tomcatDir + "bin" + "&& ./startup.sh";
        } else {
            stopCommand = "cd " + tomcatDir + "bin" + "&&shutdown.bat";
            delCommand = "cd " + tomcatDir + "webapps" + "&&rmdir /s/q ROOT";
            startCommand = "cd " + tomcatDir + "bin" + "&&startup.bat";
        }
        Process exec1 = execute(stopCommand, isLinux);
        logProcess(exec1);
        Process exec2 = execute(delCommand, isLinux);
        logProcess(exec2);

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.execute(() -> {
            Process exec3 = execute(startCommand, isLinux);
            logProcess(exec3);
        });


        return ResponseVo.success();
    }


    /**
     * tomcat重启
     *
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    @Override
    public ResponseVo reboot(FileParam fileParam) throws InterruptedException {

        String tomcatDir = fileParam.getTomcatDir();

        String stopCommand;
        String startCommand;
        boolean isLinux = isLinux();
        if (isLinux) {
            stopCommand = "cd " + tomcatDir + "bin" + "&& ./shutdown.sh";
            startCommand = "cd " + tomcatDir + "bin" + "&& ./startup.sh";
        } else {
            stopCommand = "cd " + tomcatDir + "bin" + "&&shutdown.bat";
            startCommand = "cd " + tomcatDir + "bin" + "&&startup.bat";
        }

        Process exec1 = execute(stopCommand, isLinux);
        logProcess(exec1);

        Thread.sleep(10000L);

        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.execute(() -> {
            Process exec2 = execute(startCommand, isLinux);
            logProcess(exec2);
        });


        return ResponseVo.success();
    }

    /**
     * 调用命令行执行命令
     *
     * @param commond
     * @param isLinux
     * @return
     */
    private Process execute(String commond, boolean isLinux) {
        Process exec = null;

        try {
            exec = Runtime.getRuntime().exec(
                    isLinux ? CommondEnum.LINUX_COMMOND.getCommod() : CommondEnum.WINDOWS_COMMOND.getCommod()
                            + commond);
            LogUtils.info("执行cmd[{}]", commond);
        } catch (IOException e) {
            LogUtils.error("执行cmd[{}]失败:{}", commond, e);
        }
        return exec;
    }

    /**
     * 记录命令行
     *
     * @param exec
     */
    private void logProcess(Process exec) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(exec
                .getInputStream()))) {
            //虽然cmd命令可以直接输出，但是通过IO流技术可以保证对数据进行一个缓冲。
            String msg = null;
            while ((msg = br.readLine()) != null) {
                LogUtils.info("[cmd]:{}", msg);
            }
        } catch (IOException e) {
            LogUtils.error("记录命令行失败:{}", e.getMessage(), e);
        }
    }

    /**
     * 下载附件
     *
     * @param fileParam 对象存储参数
     * @param savePath  保存地址
     * @return
     */
    private boolean download(FileParam fileParam, String savePath) {
        boolean flag = true;
        byte[] getData = null;
        try (
                InputStream inputStream = fileAction.getObject(fileParam.getBucketName(), fileParam.getObjectName());
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ) {
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            getData = bos.toByteArray();
        } catch (Exception e) {
            flag = false;
            LogUtils.error("读取存储附件失败:{}", fileParam, e);
        }

        // 文件保存位置
        File file = new File(savePath);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(getData);
        } catch (Exception e) {
            flag = false;
            LogUtils.error("保存附件失败:{}", savePath, e);
        }

        return flag;
    }

    /**
     * 判断操作系统
     *
     * @return
     */
    private boolean isLinux() {
        String os = System.getProperty("os.name");
        return os.toLowerCase().contains("linux");
    }

}
