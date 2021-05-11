package com.johnyehyo.servermanage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnyehyo.servermanage.core.bean.CommonPages;
import com.johnyehyo.servermanage.core.param.ServerQuery;
import com.johnyehyo.servermanage.core.util.PageUtils;
import com.johnyehyo.servermanage.core.vo.ResponseVo;
import com.johnyehyo.servermanage.entity.ServerInfo;
import com.johnyehyo.servermanage.mapper.ServerInfoMapper;
import com.johnyehyo.servermanage.service.IServerInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JohnYehyo
 * @since 2021-05-11
 */
@Service
@AllArgsConstructor
public class ServerInfoServiceImpl extends ServiceImpl<ServerInfoMapper, ServerInfo> implements IServerInfoService {

    private final ServerInfoMapper serverInfoMapper;

    /**
     * 服务器列表
     * @param serverQuery 查询参数
     * @return vo
     */
    @Override
    public ResponseVo pages(ServerQuery serverQuery) {
        LambdaQueryWrapper<ServerInfo> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(serverQuery.getServerName())){
            queryWrapper.eq(ServerInfo::getServerName, serverQuery.getServerName());
        }
        queryWrapper.last("and is_valid = 0");
        IPage<ServerInfo> serverInfoPage = new Page<>(serverQuery.getPage(), serverQuery.getLimit());
        serverInfoPage = serverInfoMapper.pages(serverInfoPage, serverQuery);
        return ResponseVo.success(PageUtils.restPage(serverInfoPage));
    }
}
