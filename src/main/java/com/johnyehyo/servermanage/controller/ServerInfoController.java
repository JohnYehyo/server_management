package com.johnyehyo.servermanage.controller;


import com.johnyehyo.servermanage.core.param.ServerQuery;
import com.johnyehyo.servermanage.core.util.LogUtils;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.service.IServerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 服务治理
 * </p>
 *
 * @author JohnYehyo
 * @since 2021-05-11
 */
@Api(tags = "服务治理")
@RestController
@RequestMapping(value = "serverInfo")
@AllArgsConstructor
public class ServerInfoController {

    private final IServerInfoService serverInfoService;

    /**
     * 服务器列表
     *
     * @return
     */
    @ApiOperation(value = "服务器列表查询")
    @PostMapping(value = "pages")
    public ResponseVo pages(@Valid ServerQuery serverQuery) {
        return serverInfoService.pages(serverQuery);
    }
}
