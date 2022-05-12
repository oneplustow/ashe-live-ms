package cn.oneplustow;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.AntPathMatcher;

//@SpringBootTest(classes = MessageCenterApplication.class)
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

    @Test
    public void testProperties(){
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("oneplustow");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        System.out.println(encryptor.encrypt("data.oneplustow.cn:8848"));
        System.out.println(encryptor.encrypt("mysql2020920."));
    }



    @Test
    public void test(){
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean match = antPathMatcher.match("/test/test1/{path}", "/system-contre/test/test1/123");
        System.out.println(match);
    }
}
