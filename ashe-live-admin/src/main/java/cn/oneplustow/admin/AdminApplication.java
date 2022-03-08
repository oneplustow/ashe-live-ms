package cn.oneplustow.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cc
 * @date 2020/9/17 15:42
 * admin监控启动类
 */
@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
@RestController
public class AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class,args);
    }

    @GetMapping("test")
    public String test(){
        return "test";
    }

}
