package cn.oneplustow.live.scheduled;

import cn.oneplustow.live.service.IStreamServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时计划任务
 * @author cc
 */

public class ScheduledJob {

    @Autowired
    private IStreamServerService streamServerService;

    /**
     * 定时执行心跳检测
     */
    @Scheduled
    public void heartBeatDetection(){
        streamServerService.heartBeatDetection();
    }
}
