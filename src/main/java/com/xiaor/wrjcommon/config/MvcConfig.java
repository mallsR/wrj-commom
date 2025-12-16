package com.xiaor.wrjcommon.config;

import com.xiaor.wrjcommon.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xiaoR
 * @version 1.0
 * @date 2025/12/16
 * @description 使拦截器生效
 */
@Component
// 当存在DispatcherServlet时, 才装配MvcConfig
// DispatcherServlet为SpringMVC的核心类, 此处用于判断当前服务是否为SpringMVC服务
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
