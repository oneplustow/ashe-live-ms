package cn.oneplustow.lc.scheduled;

import cn.oneplustow.lc.service.IStreamServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

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
