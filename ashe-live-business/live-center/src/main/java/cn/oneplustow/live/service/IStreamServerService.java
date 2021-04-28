package cn.oneplustow.live.service;

import cn.oneplustow.live.entity.StreamServer;
import cn.oneplustow.live.vo.QueryStreamServerDto;
import cn.oneplustow.live.vo.SaveStreamServerDto;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 流服务器信息Service接口
 * 
 * @author cc
 * @date 2021-04-09
 */
public interface IStreamServerService {

    /**
     *
     * @param streamServer
     * @return
     */
    List<StreamServer> page(QueryStreamServerDto streamServer);

    /**
     *
     * @param id
     * @return
     */
    StreamServer getStreamServerById(Long id);

    /**
     * 获取一个可用的服务器
     * @return
     */
    StreamServer getAvailable();

    /**
     * 对所有流服务器进行心跳检测
     */
    void heartBeatDetection();

    /**
     * 对指定IP的流服务器进行心跳检测
     * @param id 流服务器ID
     */
    boolean heartBeatDetectionById(Integer id);

    /**
     * 对指定的ip和port进行连接测试
     * @param ip 服务器ip
     * @param port 服务器端口
     * @return 是否连接成功
     */
    boolean heartBeatDetectionTest(String ip, Integer port);

    /**
     *
     * @param ip
     * @param port
     * @return
     * @throws Exception
     */
    boolean heartBeatDetection(String ip, Integer port)throws Exception;

    boolean addStreamServer(SaveStreamServerDto streamServer);

    boolean updateStreamServer(SaveStreamServerDto streamServer);

    boolean delById(List<Integer> asList);
}
