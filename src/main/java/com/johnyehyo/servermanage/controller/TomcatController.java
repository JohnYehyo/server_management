package com.johnyehyo.servermanage.controller;

import com.johnyehyo.servermanage.core.param.FileParam;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.service.TomcatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @description: tomcat管理
 * @author: JohnYehyo
 * @create: 2021-05-10 17:32:34
 */
@Api(tags = "tomcat管理")
@RestController
@RequestMapping(value = "tomcat")
public class TomcatController {

    @Autowired
    private TomcatService tomcatService;

    /**
     * tomcat升级
     *
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    @ApiOperation(value = "tomcat升级")
    @PostMapping(value = "upgrade")
    public ResponseVo upgrade(@Validated(FileParam.upgrade.class) @RequestBody FileParam fileParam) {
        return tomcatService.upgrade(fileParam);
    }

    /**
     * tomcat重启
     *
     * @param fileParam 对象参数
     * @return ResponseVo 结果
     */
    @ApiOperation(value = "tomcat重启")
    @PostMapping(value = "reboot")
    public ResponseVo reboot(@Validated(FileParam.reboot.class) @RequestBody FileParam fileParam) throws InterruptedException {
        return tomcatService.reboot(fileParam);
    }
}
