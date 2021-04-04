package cn.oneplustow.user;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author cc
 * @date 14/09/2020 14:07
 */
@MapperScan(value={"cn.oneplustow.user.mapper"})
@EnableSpringUtil
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.oneplustow.*"})
public class UserCenterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(UserCenterApplication.class,args);
    }
}
