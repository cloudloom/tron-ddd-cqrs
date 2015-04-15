package com.tracebucket.tron.test.mvc.config;

import com.tracebucket.tron.endpoint.OverridingRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * Created by ffl on 14-04-2015.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.tracebucket.tron.test.mvc.controller"})
public class MVCConfig extends WebMvcConfigurationSupport{

	@Bean
	@Override
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		OverridingRequestMappingHandlerMapping handlerMapping = new OverridingRequestMappingHandlerMapping();
		return handlerMapping;
	}
}
