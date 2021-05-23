package com.benbird.inner.wxapplets.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
/**
 * 项目名: wx-applets
 * 创建者: Admin
 * 创建时间: 2021/5/15
 * 描述:
 *
 * @author Admin
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        ApiInfoBuilder builder = new ApiInfoBuilder();
        builder.title("微信小程序");
        ApiInfo info = builder.build();
        docket.apiInfo(info);
        ApiSelectorBuilder selectorBuilder = docket.select();
        selectorBuilder.paths(PathSelectors.any());
        // 添加了ApiOperation注解的方法上会出现在Swagger上
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        docket = selectorBuilder.build();
        // 请求中的哪个参数是令牌字符串
        ApiKey apiKey = new ApiKey("token", "token", "header");
        List<ApiKey> apiKeyList = new ArrayList<>();
        apiKeyList.add(apiKey);
        docket.securitySchemes(apiKeyList);
        // JWT认证在Swagger中的作用域
        AuthorizationScope scope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] scopes = {scope};
        SecurityReference reference = new SecurityReference("token", scopes);
        // 令牌的作用域
        List<SecurityReference> refList = new ArrayList<>();
        refList.add(reference);
        // 令牌上下文
        SecurityContext context = SecurityContext.builder().securityReferences(refList).build();
        List<SecurityContext> cxtList = new ArrayList<>();
        cxtList.add(context);
        docket.securityContexts(cxtList);
        return docket;
    }

}
