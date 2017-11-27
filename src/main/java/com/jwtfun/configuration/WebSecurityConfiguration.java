package com.jwtfun.configuration;

import javax.annotation.Resource;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jwtfun.filter.AuthJwtFilter;
import com.jwtfun.service.CustomUserDetailsService;


/**
 * This class is used to make customisations to the WebSecurity which creates the
 * Spring Security Filter Chain.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private CustomUserDetailsService customUserDetailsService;
  private static final String HAS_ROLE_SUPER_USER = "hasRole('ADMIN') or hasRole('USER')";
  private static final String HAS_ROLE_USER = "hasRole('USER')";
  private static final String HAS_ROLE_ADMIN = "hasRole('ADMIN')";
	     

  @Override
  protected void configure(final HttpSecurity httpSecurity) throws Exception {

	  httpSecurity
      //-- We don't need CSRF because our token is invulnerable
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers(
        "/jwtfun/",
        "/jwtfun/**",
        "/jwtfun/showCreditCard",
        "/jwtfun/showCreditCard/**",
        "/jwtfun/creditCardTypePage",
        "/jwtfun/addCreditCardType/**",
        "/jwtfun/putCreditCardType",
        "/jwtfun/putCreditCardType/**"
    ).permitAll()
        .antMatchers("/jwtfun/showCreditCard").access(HAS_ROLE_SUPER_USER)
        .antMatchers("/jwtfun/showCreditCard/**").access(HAS_ROLE_SUPER_USER)
        .antMatchers("/jwtfun/creditCardTypePage").access(HAS_ROLE_ADMIN)
        .antMatchers("/jwtfun/creditCardTypePage/**").access(HAS_ROLE_ADMIN)
        .antMatchers("/jwtfun/putCreditCardType").access(HAS_ROLE_SUPER_USER)
        .antMatchers("/jwtfun/putCreditCardType/**").access(HAS_ROLE_SUPER_USER)
        .anyRequest()
        .anonymous();

    //-- Disable page caching
    httpSecurity.csrf().disable();
    httpSecurity.headers().cacheControl();

  }

  @Resource(name = "customUserDetailsService")
  public void setCustomUserDetailsService(final CustomUserDetailsService customUserDetailsService) {
    this.customUserDetailsService = customUserDetailsService;
  }

 @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurerAdapter() {
      @Override
      public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("*/**");
      }
    };
  }

  @Bean
  public RequestMappingInfoHandlerMapping requestMappingInfoHandlerMapping() {
    return new RequestMappingHandlerMapping();
  }
  
  @Bean
  public AuthJwtFilter authJwtFilter() {
    return new AuthJwtFilter();
  }

  @Bean
  public FilterRegistrationBean someFilterRegistration() {
    FilterRegistrationBean registration = new FilterRegistrationBean();

    registration.setFilter(authJwtFilter());
    registration.setName(authJwtFilter().getClass().getName());
    registration.setOrder(1);

    return registration;
  }
}