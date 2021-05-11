package com.johnyehyo.servermanage.core.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 服务信息
 * @author: JohnYehyo
 * @create: 2021-05-11 17:00:19
 */
@Data
public class TomcatInfo implements Serializable {

    private static final long serialVersionUID = 1089426781875171695L;

    /**
     * id
     */
    private int id;

    /**
     * 服务名称
     */
    private String server_name;

    /**
     * 服务器地址
     */
    private String server_url;

    /**
     * tomcat地址
     */
    private String tomcat_dir;

    /**
     * 状态
     */
    private int state;

    /**
     * 桶
     */
    private String bucketName;

    /**
     * 对象
     */
    private String objectName;
}
