package com.jias.page.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {

//    @Bean(value = "defaultApi")
//    public Docket defaultApi() {
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                // 创建接口文档的具体信息
//                .apiInfo(webApiInfo())
//                //分组名称
//                .groupName("2.X版本")
//                // 创建选择器，控制哪些接口被加入文档
//                .select()
//                //这里指定Controller扫描包路径
////                .apis(RequestHandlerSelectors.basePackage("com.github.xiaoymin.knife4j.controller"))
//                // 这里指定扫描有ApiOperation注解的类
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                // 所有路径
//                .paths(PathSelectors.any())
//                // 不包含^/inner/.*的路径
//                //.paths(input -> !input.matches("^/inner/.*"))
//                .build();
//        return docket;
//    }
//
//    private ApiInfo webApiInfo() {
//        // http://127.0.0.1:9999/admin/doc.htm
//        return new ApiInfoBuilder()
//                // 文档标题
//                .title("json的RESTful APIs")
//                // 文档描述
//                .description("构建RESTful APIs")
//                .termsOfServiceUrl("https://www.jias.com")
//                // 联系人信息
//                .contact(new Contact("json", "https://www.jias.com", "xxx@163.com"))
//                // 版本
//                .version("1.0")
//                // 版权
//                .license("JIAS")
//                // 版权地址
//                .licenseUrl("http://www.jias.link")
//                .build();
//    }

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags()!=null){
                openApi.getTags().forEach(tag -> {
                    Map<String,Object> map=new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0,100));
                    tag.setExtensions(map);
                });
            }
            if(openApi.getPaths()!=null){
                openApi.addExtension("x-test123","333");
                openApi.getPaths().addExtension("x-abb",RandomUtil.randomInt(1,100));
            }

        };
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("XXX用户系统API")
                        .version("1.0")

                        .description( "Knife4j集成springdoc-openapi示例")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")));
    }
}
