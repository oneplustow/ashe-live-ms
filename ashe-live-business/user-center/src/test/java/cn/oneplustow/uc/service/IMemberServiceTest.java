package cn.oneplustow.uc.service;

import cn.oneplustow.uc.vo.SaveMemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IMemberServiceTest {

    @Autowired
    private IMemberService memberService;

    @Test
    void insertMember() {
        SaveMemberDto saveMemberDto = new SaveMemberDto();
        saveMemberDto.setUserName("cc");
        saveMemberDto.setNickName("cc");
        saveMemberDto.setPassword("cc123");
        boolean b = memberService.insertMember(saveMemberDto);
        System.out.println(b);
    }
}