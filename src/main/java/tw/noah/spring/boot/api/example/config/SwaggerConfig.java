package tw.noah.spring.boot.api.example.config;


import com.google.common.base.Predicates;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
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
    ApiInfo info = new ApiInfoBuilder().title("Spring Boot中使用Swagger2建置RESTful APIs").description("沒有Spring就沒有Java ^_^")
        .termsOfServiceUrl("http://noah.tw").version("1.0").build();

    // /error is the default not found exception
    return new Docket(DocumentationType.SWAGGER_2).apiInfo(info).select().apis(RequestHandlerSelectors.basePackage(basePackage))
        .paths(Predicates.not(PathSelectors.regex("/error"))).build();
//            .paths(PathSelectors.any())
  }

  /**
   * production 透過 override 手法，將 swagger-ui 隱藏起來
   */
  @Profile("production")
  @RestController
  class DisableSwaggerUiController {

    @RequestMapping(value = "/swagger-ui.html", method = RequestMethod.GET)
    public void getSwagger(HttpServletResponse httpResponse) {
      httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
    }
  }
}
