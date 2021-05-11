package com.johnyehyo.servermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 视图
 * @author: JohnYehyo
 * @create: 2021-05-11 16:30:15
 */
@ApiIgnore
@Controller
@RequestMapping(value = "view")
public class ViewController {

    /**
     * 服务管理
     * @return 页面
     */
    @RequestMapping(value = "management")
    public String management(){
        return "management";
    }

    /**
     * 服务配置
     * @return 页面
     */
    @RequestMapping(value = "settings")
    public String settings(){
        return "settings";
    }
}
