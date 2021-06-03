package com.johnyehyo.servermanage.controller;

import com.johnyehyo.servermanage.ServerManageApplication;
import com.johnyehyo.servermanage.core.param.FileParam;
import com.johnyehyo.servermanage.service.TomcatService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description:
 * @author: JohnYehyo
 * @create: 2021-05-10 18:16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServerManageApplication.class})
public class TomcatTest {

    @Autowired
    private TomcatService tomcatService;

    @Test
    public void upgrade(){
        FileParam fileParam = new FileParam();
        fileParam.setBucketName("test");
        fileParam.setObjectName("file/rjcloud-sysweb.war");
        fileParam.setTomcatDir("D:/tool/apache-tomcat-8.5.55-test/");
        tomcatService.upgrade(fileParam);
    }

    @Test
    public void reboot(){
        FileParam fileParam = new FileParam();
        fileParam.setTomcatDir("D:/tool/apache-tomcat-8.5.55-test/");
        tomcatService.reboot(fileParam);
    }
}
