package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger配置
 * @author Louis
 * @date Jan 11, 2019
 */
/*
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
    	// 添加请求参数，我们这里把token作为请求头部参数传入后端
		ParameterBuilder parameterBuilder = new ParameterBuilder();  
		List<Parameter> parameters = new ArrayList<Parameter>();  
		parameterBuilder.name("token").description("令牌")
			.modelRef(new ModelRef("string")).parameterType("header").required(false).build();  
		parameters.add(parameterBuilder.build());  
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().globalOperationParameters(parameters);
//        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
//        		.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().build();
    }

}*/
