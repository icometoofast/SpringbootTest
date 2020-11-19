package com.example.springboot;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* 这个类是一个接口查看类，
*可以在tomact启动以后，通过访问
*           localhost:8080/swagger-ui.html
* 来查看项目中的一些接口信息
*
* */

@Configuration
@EnableSwagger2
public class swagger2 {
    @Bean
    public Docket createRsetApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(aapiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springboot"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo aapiInfo(){
        return new ApiInfoBuilder()
                .title("spting boot api文档")
                .description("描述")
                .termsOfServiceUrl("www.baidu.com")
                .contact("lalala")
                .version("1.0")
                .build();
    }
}
