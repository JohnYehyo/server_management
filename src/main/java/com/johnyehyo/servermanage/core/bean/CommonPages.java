package com.johnyehyo.servermanage.core.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description: fenye
 * @author: JohnYehyo
 * @create: 2021-05-11 17:18:05
 */
@Data
public class CommonPages<T> implements Serializable {

    private static final long serialVersionUID = -4044285200341495682L;

    /**
     * 总页数
     */
    private Long totalPage;

    /**
     * 总数
     */
    private Long total;

    /**
     * 数据
     */
    private List<T> list;
}
