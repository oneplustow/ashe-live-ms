package cn.oneplustow.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author cc
 * @date 14/09/2020 14:43
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(true).select()
                .apis(RequestHandlerSelectors.basePackage("cn.oneplustow.user")).paths(PathSelectors.any()).build();
    }

    @Bean
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder().version("0.0.1").description("CRM-中间件").build();
    }
}
