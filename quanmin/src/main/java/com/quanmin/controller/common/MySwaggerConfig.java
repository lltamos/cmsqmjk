package com.quanmin.controller.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by yang on 2017/8/29.
 */
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.quanmin.controller.providedtothird"})
@Configuration
public class MySwaggerConfig extends WebMvcConfigurationSupport {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.quanmin.controller.providedtothird"))
                .paths(PathSelectors.any())

                .build().host("");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2 RESTful APIs")
                .termsOfServiceUrl("http://blog.csdn.net/yz972641975")
                .version("1.1")
                .description("全民健康接口管理平台")
                .contact(new Contact("heasy","http://blog.csdn.net/yz972641975","972641975@qq.com"))
                .build();
    }
}
