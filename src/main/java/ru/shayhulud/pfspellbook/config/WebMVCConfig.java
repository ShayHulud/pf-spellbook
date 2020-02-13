package ru.shayhulud.pfspellbook.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.shayhulud.pfspellbook.config.interceptor.RequestExecutionTimeInterceptor;

/**
 * Interceptors config.
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new RequestExecutionTimeInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/webjars/**",
				"/swagger-resources/**"
			);
	}
}
