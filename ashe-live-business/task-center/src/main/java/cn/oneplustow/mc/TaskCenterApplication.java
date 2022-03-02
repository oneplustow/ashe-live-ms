package cn.oneplustow.mc;

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

/**
 * @author cc
 * @date 14/09/2020 14:07
 */
@EnableFeignClients("cn.oneplustow.api")
@MapperScan(value={"cn.oneplustow.mc.mapper"})
@EnableSpringUtil
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.oneplustow.*"})
public class TaskCenterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TaskCenterApplication.class,args);
    }
}
