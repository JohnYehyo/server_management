package com.johnyehyo.servermanage.controller;


import com.johnyehyo.servermanage.core.param.ServerInfoParam;
import com.johnyehyo.servermanage.core.param.ServerQuery;
import com.johnyehyo.servermanage.core.util.LogUtils;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.service.IServerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
     * @param serverQuery 查询条件
     * @return vo
     */
    @ApiOperation(value = "服务器列表查询")
    @PostMapping(value = "pages")
    public ResponseVo pages(@Valid ServerQuery serverQuery) {
        return serverInfoService.pages(serverQuery);
    }

    /**
     * 添加服务器信息
     * @param serverInfoParam 新增信息
     * @return vo
     */
    @ApiOperation(value = "添加服务器信息")
    @PostMapping(value = "add")
    public ResponseVo add(@Valid @RequestBody ServerInfoParam serverInfoParam) {
        return serverInfoService.add(serverInfoParam);
    }

    /**
     * 通过id查询服务器信息
     * @param id 新增信息
     * @return vo
     */
    @ApiOperation(value = "通过id查询服务器信息")
    @GetMapping(value = "info/{id}")
    public ResponseVo info(@PathVariable int id) {
        return serverInfoService.info(id);
    }

    /**
     * 更新服务器信息
     * @param serverInfoParam 更新信息
     * @return vo
     */
    @ApiOperation(value = "更新服务器信息")
    @PutMapping(value = "update")
    public ResponseVo update(@Valid @RequestBody ServerInfoParam serverInfoParam) {
        return serverInfoService.updateInfo(serverInfoParam);
    }
}
