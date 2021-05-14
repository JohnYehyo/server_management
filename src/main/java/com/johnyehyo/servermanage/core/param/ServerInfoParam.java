package com.johnyehyo.servermanage.core.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: 服务信息
 * @author: JohnYehyo
 * @create: 2021-05-13 20:25:47
 */
@Data
public class ServerInfoParam {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private int id;

    /**
     * 服务名
     */
    @ApiModelProperty(value = "服务名", required = true)
    private int serverName;

    /**
     * 服务器地址
     */
    @ApiModelProperty(value = "服务器地址", required = true)
    @NotEmpty(message = "服务器地址不能为空")
    private String serverUrl;

    /**
     * tomcat路径
     */
    @ApiModelProperty(value = "tomcat路径", required = true)
    @NotEmpty(message = "tomcat路径不能为空")
    private String tomcatDir;

    /**
     * 存储桶
     */
    @ApiModelProperty(value = "存储桶", required = true)
    @NotEmpty(message = "存储桶不能为空")
    private String bucketName;

    /**
     * 对象名
     */
    @ApiModelProperty(value = "对象名", required = true)
    @NotEmpty(message = "对象名不能为空")
    private String objectName;
}
