package cn.oneplustow.live.service.impl;

import cn.oneplustow.config.db.util.PageUtil;
import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.mapper.StreamServerMapper;
import cn.oneplustow.live.service.IStreamServerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流服务器信息Service业务层处理
 *
 * @author cc
 * @date 2021-04-09
 */
@Service
public class StreamServerServiceImpl extends ServiceImpl<StreamServerMapper, StreamServer> implements IStreamServerService {
    public List<StreamServer> page(StreamServer streamServer){
        PageUtil.startPage();
        QueryWrapper<StreamServer> queryWrapper = new QueryWrapper<StreamServer>();
        if (StringUtils.isNotBlank(streamServer.getServerName())){
            queryWrapper.like("server_name" ,streamServer.getServerName());
        }
        if(StringUtils.isNotBlank(streamServer.getIp())){
            queryWrapper.eq("ip" ,streamServer.getIp());
        }
        if(streamServer.getPort() != null){
            queryWrapper.eq("port" ,streamServer.getPort());
        }
        if(StringUtils.isNotBlank(streamServer.getStatus())){
            queryWrapper.eq("status" ,streamServer.getStatus());
        }
        List<StreamServer> list = this.list(queryWrapper);
        return list;
    }

}
