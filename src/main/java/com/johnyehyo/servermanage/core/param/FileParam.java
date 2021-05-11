package com.johnyehyo.servermanage.core.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @description: 附件参数
 * @author: JohnYehyo
 * @create: 2021-05-10 17:54:58
 */
@Data
public class FileParam {


    public interface upgrade {
    }

    public interface reboot {
    }


    /**
     * tomcat路径
     */
    @ApiModelProperty(value = "tomcat路径", required = true)
    @NotEmpty(
            groups = {FileParam.upgrade.class, FileParam.reboot.class},
            message = "tomcat路径不能为空"
    )
    private String tomcatDir;

    /**
     * 存储桶
     */
    @ApiModelProperty(value = "存储桶", required = true)
    @NotEmpty(
            groups = {FileParam.upgrade.class},
            message = "存储桶不能为空"
    )
    private String bucketName;

    /**
     * 对象路径
     */
    @ApiModelProperty(value = "对象路径", required = true)
    @NotEmpty(
            groups = {FileParam.upgrade.class},
            message = "对象路径不能为空"
    )
    private String objectName;

}
