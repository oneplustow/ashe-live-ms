package cn.oneplustow.lc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author CC
 * @title: RandomCodeGeneratorConfig
 * @projectName ashe-live-ms
 * @description:
 * @date 2022/4/2917:28
 */
@Configuration
public class RandomCodeGeneratorConfig {

    @Value(value = "${randomCode.disturb}")
    private String disturb;

    @Bean
    public RandomCodeGenerator randomCodeGenerator(){
        return new RandomCodeGenerator(RandomCodeGenerator.BASE_57,disturb);
    }
}
