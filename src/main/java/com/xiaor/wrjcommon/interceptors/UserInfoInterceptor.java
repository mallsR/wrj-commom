package com.xiaor.wrjcommon.interceptors;

import cn.hutool.core.util.StrUtil;
import com.xiaor.wrjcommon.utils.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xiaoR
 * @version 1.0
 * @date 2025/12/4
 * @description  登录校验: 2. 用户信息拦截器: 后续被各个微服务引用, 用于接收gateway转发的请求, 并从中获取用户信息
 */
@Slf4j
@Component
public class UserInfoInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("用户信息拦截器");
        // 1. 获取登录用户信息
        String userInfo = request.getHeader("user-info");

        // 2. 判断是否获取了用户,如果有,存入ThreadLocal中
        if(StrUtil.isNotBlank(userInfo)) {      // isNotBlank, 验证字符串不为null,不为"",不为" "等一系列不合规字符串
            UserContext.setUser(Long.valueOf(userInfo));
        }

        // 3. 放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("用户信息拦截器结束");
        // 4. 清理用户
        UserContext.removeUser();
    }
}
