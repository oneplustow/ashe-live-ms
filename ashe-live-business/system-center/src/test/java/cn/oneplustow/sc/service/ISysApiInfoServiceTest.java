package cn.oneplustow.sc.service;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;


@SpringBootTest
public class ISysApiInfoServiceTest {

    @Autowired
    ISysApiInfoService sysApiInfoService;

    @Test
    public void init() {
        sysApiInfoService.init();
    }
}