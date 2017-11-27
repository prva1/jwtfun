package com.jwtfun.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.jwtfun.configuration.security.service.CustomSecurityContext;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public static final String PREFIX = "/WEB-INF/jsp/";
	public static final String SUFIX = ".jsp";

	@Override
	public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix(PREFIX);
		resolver.setSuffix(SUFIX);

		return resolver;
	}
	
	@Bean
	  public CustomSecurityContext customSecurityContext() {
	    return new CustomSecurityContext();
	  }
}
