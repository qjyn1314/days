package com.day.parent;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * <p>
 * explain:工程中单个服务的swagger文档配置
 * </p >
 *
 * @author wangjunming
 * @since 2020-01-17 10:20
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    private static final String ADDRESS = "127.0.0.1";

    @Bean
    public Docket createRestApi() {
        List<RequestParameter> pars = new ArrayList<>();
        RequestParameterBuilder versionPar = new RequestParameterBuilder().description("token")
                .in(ParameterType.HEADER).name("TOKEN").required(false)
                .query(param -> param.model(model -> model.scalarModel(ScalarType.STRING)));
        pars.add(versionPar.build());
        // 支持的通讯协议集合
        String[] protocols = {"https", "http"};
        return new Docket(DocumentationType.OAS_30)
                .pathMapping("/")
                .host(ADDRESS)
                .apiInfo(apiInfo())
                .globalRequestParameters(pars)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .protocols(new HashSet<>(Arrays.asList(protocols)));
    }

    private ApiInfo apiInfo() {
        String licenseUrl = "http://%s:%s/swagger-ui/index.html";
        final String port = DaysProperties.getPort();
        licenseUrl = String.format(licenseUrl, ADDRESS, port);
        return new ApiInfoBuilder()
                .title("CALM-GATEWAY-RESTFUL APIS")
                .description("Mr.Wang~~搭建!!")
                .licenseUrl(licenseUrl)
                .version("1.0")
                .build();
    }

}
