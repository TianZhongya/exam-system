package com.example.tzy.demo.http.config;

import com.example.tzy.demo.http.Interceptor.AccessLogInterceptor;
import com.example.tzy.demo.http.Interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Tianzy
 * @create: 2020-11-13 20:56
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AccessLogInterceptor accessLogInterceptor;
    private final AuthInterceptor authInterceptor;

    public WebMvcConfig(AccessLogInterceptor accessLogInterceptor, AuthInterceptor authInterceptor) {
        this.accessLogInterceptor = accessLogInterceptor;
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accessLogInterceptor)
                .addPathPatterns("/**")
                .order(Integer.MIN_VALUE + 1);
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/api/**")
                .order(Integer.MIN_VALUE);
    }
}
