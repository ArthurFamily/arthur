package com.cloud.arthur.config;

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
 * 提供接口的服务需要引入此配置.
 * eureka中home-page-url配置http://localhost:${server.port}/swagger-ui.html地址，通过注册中心直接点击进入页面
 * Created by chenzhen on 2017/8/4.
 */
@Configuration
@EnableSwagger2 // 启用Swagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {// 创建API基本信息
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cloud.arthur.controller"))// 扫描该包下的所有需要在Swagger中展示的API，@ApiIgnore注解标注的除外
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {// 创建API的基本信息，这些信息会在Swagger UI中进行显示
        return new ApiInfoBuilder()
                .title("ARTHUR-MANAGE-SERVICEWEB 接口 APIs")// API 标题
                .description("服务端管理模块对外暴露接口API")// API描述
                .contact("chenzhen@163.com")// 联系人
                .version("1.0")// 版本号
                .build();
    }
}
