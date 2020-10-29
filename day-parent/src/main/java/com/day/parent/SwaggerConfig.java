package com.day.parent;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * explain:工程中的swagger文档配置
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 10:20
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {

    /**
     * 添加swagger-ui的资源文件访问权限
     *
     * @param registry:
     * @author wangjunming
     * @since 2020/1/17 10:59
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private static final String ADDRESS = "127.0.0.1";

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> pars = new ArrayList<>();
        RequestParameterBuilder versionPar = new RequestParameterBuilder().description("token")
                .in(ParameterType.HEADER).name("TOKEN").required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        pars.add(versionPar.build());
        return new Docket(DocumentationType.OAS_30).host(ADDRESS)
                .apiInfo(apiInfo())
                .globalRequestParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        String licenseUrl = "http://%s:%s/swagger-ui/index.html";
        final String port = DaysProperties.getPort();
        return new ApiInfoBuilder()
                .title("DAYS~--RESTFUL APIS")
                .description("Mr.Wang~~搭建!!")
                .licenseUrl(String.format(licenseUrl, ADDRESS, port))
                .version("1.0")
                .build();
    }

}
