package com.photo.common.filter;

import com.alibaba.fastjson.JSON;
import com.photo.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {

    private static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("拦截的请求：{}", request.getRequestURI());

        String[] url = new String[]{
                "/api/v1/admin/login",
                "/api/v1/client/login"
        };

        boolean check = check(url, requestURI);
//        判断放行
        if (check) {
            log.info("本次请求{}不需要处理", requestURI);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
//        登录放行
        if (request.getSession().getAttribute("user") != null) {
            log.info("本次请求已经登陆,用户id:{}", request.getSession().getAttribute("user"));
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(Result.error(404, "NotFound")));
//        filterChain.doFilter(servletRequest, servletResponse);
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