package cn.oneplustow.gateway.config;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.config.listener.Listener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * @author cc
 * @date 2020/10/27 20:00
 */
@Slf4j
public class NacosRouteDefinitionRepository implements RouteDefinitionRepository {
    private String dataId = "gateway";
    private String groupId = "dev";
    private ApplicationEventPublisher publisher;
    private NacosConfigManager nacosConfigManager;

    public NacosRouteDefinitionRepository(ApplicationEventPublisher publisher, NacosConfigManager nacosConfigManager ) {
        this.publisher = publisher;
        this.nacosConfigManager = nacosConfigManager;
        addNaconsListen();
    }

    private void addNaconsListen() {
        try {
            nacosConfigManager.getConfigService().addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }
                @Override
                public void receiveConfigInfo(String configInfo) {
                    publisher.publishEvent(new RefreshRoutesEvent(this));
                }
            });
        }catch (Exception e){
            log.error("nacos-addListener-error", e);
        }

    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        try {
            String config = nacosConfigManager.getConfigService().getConfig(dataId, groupId, 2000);
            List<RouteDefinition> routeDefinitions = JSONArray.parseArray(config, RouteDefinition.class);
            return Flux.fromIterable(routeDefinitions);
        }catch (Exception e){
            log.error("nacos get config error：",e);
        }
        return Flux.fromIterable(CollUtil.newArrayList());
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return null;
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return null;
    }
}
