package cn.oneplustow.sc;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author cc
 * @date 14/09/2020 14:07
 */
@MapperScan(value={"cn.oneplustow.sc.mapper"})
@EnableSpringUtil
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"cn.oneplustow.*"},exclude = DataSourceAutoConfiguration.class)
public class SystemCenterApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SystemCenterApplication.class,args);
    }
}
