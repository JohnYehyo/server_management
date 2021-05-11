package com.johnyehyo.servermanage.service;

import com.johnyehyo.servermanage.core.param.ServerQuery;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.entity.ServerInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JohnYehyo
 * @since 2021-05-11
 */
public interface IServerInfoService extends IService<ServerInfo> {

    /**
     * 服务器列表
     * @param serverQuery 查询参数
     * @return vo
     */
    ResponseVo pages(ServerQuery serverQuery);
}
