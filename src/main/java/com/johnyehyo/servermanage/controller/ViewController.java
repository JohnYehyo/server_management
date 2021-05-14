package com.johnyehyo.servermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 服务配置表单
     * @return 页面
     */
    @RequestMapping(value = "settings_form")
    public String settings_form(@RequestParam(required = false, defaultValue = "0") int id, Model model){
        model.addAttribute("id", id);
        return "settings_form";
    }
}
