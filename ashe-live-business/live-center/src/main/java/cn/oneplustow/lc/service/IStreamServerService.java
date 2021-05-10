package cn.oneplustow.lc.service;

import cn.oneplustow.lc.entity.StreamServer;
import cn.oneplustow.lc.vo.QueryStreamServerDto;
import cn.oneplustow.lc.vo.SaveStreamServerDto;

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
    boolean heartBeatDetectionById(Long id);

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
