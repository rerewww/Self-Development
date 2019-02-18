package com.example.demo.config;

import com.example.demo.interceptor.HttpSevletRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by son on 2019-02-18.
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpSevletRequestInterceptor())
				.addPathPatterns("/*");
	}
}
