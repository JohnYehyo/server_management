package com.johnyehyo.servermanage.core.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 服务查询
 * @author: JohnYehyo
 * @create: 2021-05-10 17:54:58
 */
@Data
public class ServerQuery {

    /**
     * 服务名
     */
    @ApiModelProperty(value = "服务名")
//    @NotEmpty(message = "服务名不能为空")
    private String serverName;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    private int page;

    /**
     * 每页条目
     */
    @ApiModelProperty(value = "每页条目")
    private int limit;
}
