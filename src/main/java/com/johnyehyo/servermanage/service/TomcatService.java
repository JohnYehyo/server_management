package com.johnyehyo.servermanage.service;

import com.johnyehyo.servermanage.core.param.FileParam;
import com.johnyehyo.servermanage.core.vo.ResponseVo;

/**
 * @description: tomcat管理
 * @author: JohnYehyo
 * @create: 2021-05-10 17:31:55
 */
public interface TomcatService {

    /**
     * tomcat升级
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    ResponseVo upgrade(FileParam fileParam);

    /**
     * tomcat重启
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    ResponseVo reboot(FileParam fileParam);
}
