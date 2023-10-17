package com.photo.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.photo.common.utils.Result;
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

/**
 * 拦截器，未生效，目前不用
 */
//@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return HandlerInterceptor.super.preHandle(request, response, handler);
        log.info("preHandle....");

        String requestURI = request.getRequestURI();
        String[] url = new String[]{
                "/api/v1/admin/login",
                "/api/v1/client/login",
                "/api/v1/client/register"
        };

        boolean check = check(url, requestURI);

        // 放行login
        if (check) {
            log.info("本次请求{}不需要拦截", requestURI);
            return true;
        }

        String[] urlAdmin = new String[]{
                "/api/v1/admin/*"
//                "/api/v1/admin/logout",
//                "/api/v1/admin/size",
//                "/api/v1/admin/frame",
//                "/api/v1/admin/order",
//                "/api/v1/admin/order/cancel/{id}",
//                "/api/v1/admin/order/complete/{id}",
//                "/api/v1/admin/user",
//                "/api/v1/admin/user/reset/{id}",
//                "/api/v1/admin//user/{id}",
//                "/api/v1/admin/cart/reset/{id}",
//                "/api/v1/admin/admin",
//                "/api/v1/admin/admin/reset/{id}",
//                "/api/v1/admin/admin/{id}",
        };
        String[] urlClient = new String[]{
                "/api/v1/client/*",
//                "/api/v1/client/logout",
//                "/api/v1/client/size",
//                "/api/v1/client/frame",
//                "/api/v1/client/order",
//                "/api/v1/client/order/cancel/{id}",
//                "/api/v1/client/photo",
//                "/api/v1/client/photo/size/{id}",
//                "/api/v1/client/photo/frame/{photo_id}/{frame_id}",
//                "/api/v1/client/photo/{id}",
//                "/api/v1/client/cart",
        };
        Integer userAdmin = (Integer) request.getSession().getAttribute("admin_id");
        Integer userClient = (Integer) request.getSession().getAttribute("client_id");

        User userA = userMapper.userById(userAdmin);
        User userC = userMapper.userById(userClient);

//        if (userA == null) {
//            log.info("Admin用户未登录");
//            response.getWriter().write(JSON.toJSONString(Result.error(401, "unauthenticated")));
//        }
//        if (userC == null){
//            log.info("Client用户未登录");
//            response.getWriter().write(JSON.toJSONString(Result.error(401, "unauthenticated")));
//        }

//        // 拦截Client访问admin
//        if (userA != null && userA.getIsAdmin() == 1) {
//            boolean checkAdmin = check(urlAdmin, requestURI);
//            log.info("放行admin");
//            log.info("本次请求是admin登陆的: {}", userA.getIsAdmin());
//            return checkAdmin;
//        }
//        // 拦截Admin 访问Client
//        if (userC != null && userC.getIsAdmin() == 0) {
//            boolean checkClient = check(urlClient, requestURI);
//            log.info("放行client");
//            log.info("本次请求是client登陆的: {}", userC.getIsAdmin());
//            return checkClient;
//        }
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

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
