package com.nowcoder.community.config;

import com.nowcoder.community.controller.interceptor.AlphaInterceptor;
import com.nowcoder.community.controller.interceptor.LoginTicketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 王修豪
 * @version 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AlphaInterceptor alphaInterceptor;

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册接口 靠传进来的对象注册Interceptor
        registry.addInterceptor(alphaInterceptor)//默认全部拦截
                .excludePathPatterns("/**/*.css","/**/*.png","/**/*.jpg","/**/*.jpeg")//除了这些都被拦截
                .addPathPatterns("/register","/login");//指定拦截

        registry.addInterceptor(loginTicketInterceptor)//默认全部拦截
                .excludePathPatterns("/**/*.css","/**/*.png","/**/*.jpg","/**/*.jpeg");//除了这些都被拦截
    }
}
