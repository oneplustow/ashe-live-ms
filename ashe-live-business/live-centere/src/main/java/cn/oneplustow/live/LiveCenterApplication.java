package cn.oneplustow.live;

/**
 * @author cc
 * @date 2020/11/3 13:57
 */

import cn.hutool.extra.spring.EnableSpringUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author cc
 * @date 14/09/2020 14:07
 */
@EnableScheduling
@EnableFeignClients("cn.oneplustow.api")
@MapperScan(value={"cn.oneplustow.live.mapper"})
@EnableSpringUtil
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.oneplustow.*"})
public class LiveCenterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LiveCenterApplication.class,args);
    }
}
