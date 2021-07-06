package cn.oneplustow;

import cn.oneplustow.mc.MessageCenterApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.util.AntPathMatcher;

@SpringBootTest(classes = MessageCenterApplication.class)
public class SpringMvcTest {

    @Autowired
    private Environment environment;
//
//    @Autowired
//    private StringEncryptor stringEncryptorl;

    @Test
    public void test1(){
        System.out.println(environment.getProperty("cc"));
    }
//
//    @Test
//    public void testProperties(){
//        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
//        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
//        config.setPassword("password");
//        config.setAlgorithm("PBEWithMD5AndDES");
//        config.setKeyObtentionIterations("1000");
//        config.setPoolSize("1");
//        config.setProviderName("SunJCE");
//        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
//        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
//        config.setStringOutputType("base64");
//        encryptor.setConfig(config);
//        System.out.println(encryptor.encrypt("ashe-live"));
//        System.out.println(encryptor.encrypt("ashe-live-prod"));
//    }



    @Test
    public void test(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/test/test1/{path}", "/system-contre/test/test1/123");
        System.out.println(match);
    }
}
