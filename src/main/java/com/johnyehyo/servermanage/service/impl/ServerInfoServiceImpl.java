package com.johnyehyo.servermanage.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.johnyehyo.servermanage.core.bean.CommonPages;
import com.johnyehyo.servermanage.core.param.ServerInfoParam;
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
 * 服务实现类
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
     *
     * @param serverQuery 查询参数
     * @return vo
     */
    @Override
    public ResponseVo pages(ServerQuery serverQuery) {
        LambdaQueryWrapper<ServerInfo> queryWrapper = new LambdaQueryWrapper<>();
        if (null != serverQuery.getServerName()) {
            queryWrapper.eq(ServerInfo::getServerName, serverQuery.getServerName());
        }
        queryWrapper.last("and is_valid = 0");
        IPage<ServerInfo> serverInfoPage = new Page<>(serverQuery.getPage(), serverQuery.getLimit());
        serverInfoPage = serverInfoMapper.pages(serverInfoPage, serverQuery);
        return ResponseVo.success(PageUtils.restPage(serverInfoPage));
    }

    /**
     * 添加服务器信息
     *
     * @param serverInfoParam 服务器信息
     * @return vo
     */
    @Override
    public ResponseVo add(ServerInfoParam serverInfoParam) {
        ServerInfo serverInfo = new ServerInfo();
        BeanUtil.copyProperties(serverInfoParam, serverInfo);
        int count = serverInfoMapper.insert(serverInfo);
        if (count > 0) {
            return ResponseVo.success();
        }
        return ResponseVo.error();
    }

    /**
     * 通过id查询服务器信息
     *
     * @param id 新增信息
     * @return vo
     */
    @Override
    public ResponseVo info(int id) {
        return ResponseVo.success(serverInfoMapper.selectById(id));
    }

    /**
     * 更新服务器信息
     *
     * @param serverInfoParam 更新信息
     * @return vo
     */
    @Override
    public ResponseVo updateInfo(ServerInfoParam serverInfoParam) {
        ServerInfo serverInfo = new ServerInfo();
        BeanUtil.copyProperties(serverInfoParam, serverInfo);
        int count = serverInfoMapper.updateById(serverInfo);
        if (count > 0) {
            return ResponseVo.success();
        }
        return ResponseVo.error();
    }
}


