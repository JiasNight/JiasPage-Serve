package com.jias.page.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean(value = "defaultApi")
    public Docket defaultApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                // 创建接口文档的具体信息
                .apiInfo(webApiInfo())
                //分组名称
                .groupName("2.X版本")
                // 创建选择器，控制哪些接口被加入文档
                .select()
                //这里指定Controller扫描包路径
//                .apis(RequestHandlerSelectors.basePackage("com.github.xiaoymin.knife4j.controller"))
                // 这里指定扫描有ApiOperation注解的类
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 所有路径
                .paths(PathSelectors.any())
                // 不包含^/inner/.*的路径
                //.paths(input -> !input.matches("^/inner/.*"))
                .build();
        return docket;
    }

    private ApiInfo webApiInfo() {
        // http://127.0.0.1:9999/admin/doc.htm
        return new ApiInfoBuilder()
                // 文档标题
                .title("json的RESTful APIs")
                // 文档描述
                .description("构建RESTful APIs")
                .termsOfServiceUrl("https://www.jias.com")
                // 联系人信息
                .contact(new Contact("json", "https://www.jias.com", "xxx@163.com"))
                // 版本
                .version("1.0")
                // 版权
                .license("JIAS")
                // 版权地址
                .licenseUrl("http://www.jias.link")
                .build();
    }
}
