package cn.oneplustow.lc.service.impl;

import cn.oneplustow.lc.service.IPlayRoomService;
import cn.oneplustow.lc.vo.PlayRoomDetailVo;
import cn.oneplustow.lc.vo.PlayRoomPageVo;
import cn.oneplustow.lc.vo.PlayRoomQueryCriteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PlayRoomServiceImplTest {

    @Autowired
    private IPlayRoomService playRoomService;

    @Test
    void selectPage() {
        PlayRoomQueryCriteria criteria = new PlayRoomQueryCriteria();
        criteria.setName("CC");
        List<PlayRoomPageVo> playRoomPageVos = playRoomService.selectPage(criteria);

        System.out.println(playRoomPageVos);
    }

    @Test
    void openUp() {
        boolean b = playRoomService.openUp("CC的直播间", 0L);
        System.out.println(b);
    }

    @Test
    void startLive() {
        PlayRoomDetailVo playRoomDetailVo = playRoomService.startLive(1L);
        System.out.println(playRoomDetailVo);
    }

    @Test
    void stopLive() {
        PlayRoomDetailVo playRoomDetailVo = playRoomService.stopLive(1L);
        System.out.println(playRoomDetailVo);
    }
}