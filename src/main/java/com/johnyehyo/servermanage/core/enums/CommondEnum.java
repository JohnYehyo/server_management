package com.johnyehyo.servermanage.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 命令类型
 * @author: JohnYehyo
 * @create: 2021-05-18 11:01:12
 */
@Getter
@AllArgsConstructor
public enum CommondEnum {

    /**
     * commod
     */
    LINUX_COMMOND("/bin/sh -c "),
    WINDOWS_COMMOND("cmd /c ");

    private String commod;
}
