package com.johnyehyo.servermanage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @description: 系统
 * @author: JohnYehyo
 * @create: 2021-05-11 15:17:02
 */
@ApiIgnore
@Controller
public class SysIndexController {

    @RequestMapping(value = {
                "",
            "index"
    })
    public String index(){
        return "index";
    }
}
