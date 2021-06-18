package cn.oneplustow;

import cn.oneplustow.mc.MessageCenterApplication;
import cn.oneplustow.mc.controller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;

import java.util.List;

//
public class SpringMvcTest {



    @Test
    public void test(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/test/test1/{path}", "/system-contre/test/test1/123");
        System.out.println(match);
    }
}
