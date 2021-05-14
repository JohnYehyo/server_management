package com.johnyehyo.servermanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author JohnYehyo
 * @since 2021-05-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ServerInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 服务名
     */
    private int serverName;

    /**
     * 服务地址
     */
    private String serverUrl;

    /**
     * tomcat路径
     */
    private String tomcatDir;

    /**
     * 状态 0 正常 1 未启动
     */
    private int state;

    /**
     * 存储桶
     */
    private String bucketName;

    /**
     * 对象名
     */
    private String objectName;

    /**
     * 伪删除 0 正常 1 删除
     */
    private int isValid;


}
