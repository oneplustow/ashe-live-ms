package cn.oneplustow.live.service;

import cn.oneplustow.live.entity.StreamServer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 流服务器信息Service接口
 * 
 * @author cc
 * @date 2021-04-09
 */
public interface IStreamServerService extends IService<StreamServer>{

    /**
     *
     * @param streamServer
     * @return
     */
    List<StreamServer> page(StreamServer streamServer);
}
