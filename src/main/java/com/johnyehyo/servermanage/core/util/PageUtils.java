package com.johnyehyo.servermanage.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.johnyehyo.servermanage.core.bean.CommonPages;

/**
 * @description: 分页工具
 * @author: JohnYehyo
 * @create: 2021-05-11 19:45:32
 */
public class PageUtils {

    public static <T> CommonPages<T> restPage(IPage<T> info) {
        if (null == info) {
            return null;
        }
        CommonPages<T> result = new CommonPages<>();
        result.setTotalPage(info.getPages());
        result.setTotal(info.getTotal());
        result.setList(info.getRecords());
        return result;
    }
}
