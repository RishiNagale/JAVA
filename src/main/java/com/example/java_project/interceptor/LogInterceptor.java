package com.example.java_project.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
 
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class LogInterceptor implements HandlerInterceptor {
 
    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);
 
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.info("Request URL::preHandler " + request.getRequestURL().toString() + " Sent to Handler :: " + handler);
        return true;
    }
 
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        logger.info("Handler Execution::posthandler " + handler + " Response Status:: " + response.getStatus());
    }
 
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) throws Exception {
        logger.info("Request Completed::afterCompletion " + request.getRequestURL().toString());
    }
}