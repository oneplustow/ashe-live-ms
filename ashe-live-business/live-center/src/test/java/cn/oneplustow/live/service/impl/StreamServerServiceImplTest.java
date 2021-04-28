package cn.oneplustow.live.service.impl;

import cn.oneplustow.live.LiveCenterApplication;
import cn.oneplustow.live.mapstruct.PlayRoom2SavePlayRoomDto;
import cn.oneplustow.live.service.IStreamServerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LiveCenterApplication.class)
class StreamServerServiceImplTest {

    @Autowired
    private PlayRoom2SavePlayRoomDto playRoom2SavePlayRoomDto;

    @Test
    void test(){
        System.out.println(playRoom2SavePlayRoomDto.getClass().getName());
    }

    @Autowired
    IStreamServerService iStreamServerService;

    @Test
    void heartBeatDetection() {
        iStreamServerService.heartBeatDetection();
    }
}