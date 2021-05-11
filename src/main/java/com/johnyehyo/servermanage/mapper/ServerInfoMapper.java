package com.johnyehyo.servermanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.johnyehyo.servermanage.core.param.ServerQuery;
import com.johnyehyo.servermanage.entity.ServerInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JohnYehyo
 * @since 2021-05-11
 */
public interface ServerInfoMapper extends BaseMapper<ServerInfo> {

    /**
     * 服务器列表
     * @param serverInfoPage 分页条件
     * @param serverQuery 查询条件
     * @return 分页结果
     */
    IPage<ServerInfo> pages(IPage<ServerInfo> serverInfoPage, @Param("param") ServerQuery serverQuery);
}
