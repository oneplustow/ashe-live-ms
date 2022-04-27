package cn.oneplustow.sc.service;

import org.junit.jupiter.api.Test;


/**
 * @author CC
 * @title: TestMain
 * @projectName ashe-live-ms
 * @description:
 * @date 2022/4/279:48
 */
public class TestMain {
    @Test
    public void randomCodeContextTest(){
        RandomCodeGenerator context = new RandomCodeGenerator(RandomCodeGenerator.BASE_33,"cc");

        for (int i = 33; i <= 100; i++) {
            String code = context.gengerCode(i);
            System.out.println(i+":"+code + ":" + context.verifyCode(code));
        }
    }
}
