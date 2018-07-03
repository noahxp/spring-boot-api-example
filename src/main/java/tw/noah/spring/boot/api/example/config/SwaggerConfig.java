package tw.noah.spring.boot.api.example.config;


import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Log4j2
public class SwaggerConfig {

  private final String basePackage = "tw.noah.spring.boot.api.example";

  @Bean
  public Docket productApi() {
    ApiInfo info =  new ApiInfoBuilder()
        .title("Spring Boot中使用Swagger2建置RESTful APIs")
        .description("沒有Spring就沒有Java ^_^")
        .termsOfServiceUrl("http://noah.tw")
        .version("1.0")
        .build();


    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(info)
        .select()
        .apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(PathSelectors.any())
        .build();
  }

}
