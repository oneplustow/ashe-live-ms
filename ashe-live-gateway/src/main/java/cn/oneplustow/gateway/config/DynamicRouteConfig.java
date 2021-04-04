package cn.oneplustow.gateway.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cc
 * @date 2020/10/27 20:12
 */
@Configuration
@ConditionalOnProperty(prefix = "fog.gateway.dynamicRoute",name = "enable",havingValue = "true")
public class DynamicRouteConfig {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Configuration
    @ConditionalOnProperty(prefix = "fog.gateway.dynamicRoute",name = "type",havingValue = "nacos")
    public class NacosDynamicRoute{
        @Autowired
        private NacosConfigManager nacosConfigManager;

        @Bean
        public NacosRouteDefinitionRepository nacosRouteDefinitionRepository(){
            return new NacosRouteDefinitionRepository(publisher,nacosConfigManager);
        }
    }
}
