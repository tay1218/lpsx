package com.example.lpsx.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.lpsx.common.interceptor.JwtInterceptor;

import jakarta.annotation.Resource;

/**
 * WebMvc 配置 — 注册 JWT 拦截器
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private JwtInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/api/**")           // 拦截所有 /api/**
                .excludePathPatterns(
                        "/api/auth/**",               // 登录/手机解密不拦截
                        "/api/schools/**",            // 学校列表、详情、对比不拦截
                        "/api/home/**",               // 首页不拦截
                        "/api/assessment/**",         // 择校匹配不拦截
                        "/v3/api-docs/**",            // Knife4j 文档
                        "/swagger-ui/**",
                        "/doc.html",
                        "/swagger-ui.html"
                );
    }
}
