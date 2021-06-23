package ntou.cs.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
    	 return new Docket(DocumentationType.SWAGGER_2)
                 // 提供 API 相關的資訊，不想設定可以跳過這個 apiInfo call
                 .apiInfo(apiInfo())
                 .enable(true)
                 .select()
                 //RequestHandlerSelectors.any() 代表所有的 REST API
                 .apis(RequestHandlerSelectors.any())
                 .paths(PathSelectors.any())
                 .build();
    }

    //設定Swagger資訊
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
                .title("海大論壇系統API文件")
                .description("海大論壇系統API文件")
                .contact(new Contact("網際服務軟體工程", "", "tset01@gmail.com"))
                .version("1.0")
                .build();
    }
	
}
