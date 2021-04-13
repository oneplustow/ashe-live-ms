package cn.oneplustow.live.service.impl;

import cn.oneplustow.live.LiveCenterApplication;
import cn.oneplustow.live.service.IStreamServerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LiveCenterApplication.class)
class StreamServerServiceImplTest {
    @Autowired
    IStreamServerService iStreamServerService;

    @Test
    void heartBeatDetection() {
        iStreamServerService.heartBeatDetection();
    }
}