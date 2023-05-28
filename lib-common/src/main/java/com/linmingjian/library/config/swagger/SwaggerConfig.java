package com.linmingjian.library.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger 配置
 *
 * @author lin
 * @since 2023-05-27
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Value("${security.tokenHeader}")
    private String tokenHeader;
    @Autowired
    private SwaggerProperties properties;

    @Bean
    public Docket createRestApi() {
        // 配置通用响应
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("请求成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("参数错误").build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("没有认证").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("没有访问权限").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("找不到资源").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("服务器内部错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .enable(properties.isEnabled())
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("^(?!/error).*"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(buildSecuritySchemes())
                .securityContexts(buildSecurityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .description("图书管理系统接口文档描述")
                .title("图书管理系统接口文档")
                .version("1.0.0")
                .build();
    }

    private List<? extends SecurityScheme> buildSecuritySchemes() {
        //设置请求头信息
        List<SecurityScheme> securitySchemes = new ArrayList<>();
        ApiKey apiKey = new ApiKey(tokenHeader, tokenHeader, "header");
        securitySchemes.add(apiKey);
        return securitySchemes;
    }

    private List<SecurityContext> buildSecurityContexts() {
        //设置需要登录认证的路径
        List<SecurityContext> securityContexts = new ArrayList<>();
        // ^(?!auth).*$ 表示所有包含auth的接口不需要使用securitySchemes即不需要带token
        securityContexts.add(getContextByPath());
        return securityContexts;
    }

    private SecurityContext getContextByPath() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(s -> s.matches("^(?!/auth).*$"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> securityReferences = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        securityReferences.add(new SecurityReference(tokenHeader, authorizationScopes));
        return securityReferences;
    }
}
