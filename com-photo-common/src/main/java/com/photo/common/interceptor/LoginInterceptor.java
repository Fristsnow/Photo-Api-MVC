package com.photo.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.photo.common.Result;
import com.photo.mapper.UserMapper;
import com.photo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

//    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        String requestURI = request.getRequestURI();
        String[] urlAdmin = new String[]{
                "/api/v1/admin/size",
                "/api/v1/admin/frame",
                "/api/v1/admin/order",
                "/api/v1/admin/order/cancel/{id}",
                "/api/v1/admin/order/complete/{id}",
                "/api/v1/admin/user",
                "/api/v1/admin/user/reset/{id}",
                "/api/v1/admin//user/{id}",
                "/api/v1/admin/cart/reset/{id}",
                "/api/v1/admin/admin",
                "/api/v1/admin/admin/reset/{id}",
                "/api/v1/admin/admin/3",
        };
        Integer user = (Integer) request.getSession().getAttribute("id");
        User user1 = userMapper.userById(user);

        if (user1.getIsAdmin() != 1) {
            response.getWriter().write(JSON.toJSONString(Result.error(409, "你没有权限访问")));
        }

//        boolean checkAdmin = checkAdmin(urlAdmin, requestURI);
//        if (checkAdmin){
//            return true;
//        }
//        request.
//        log.info("我来拦截is_admin");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    public boolean checkAdmin(String[] urls, String requestURI){
        for (String url : urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
