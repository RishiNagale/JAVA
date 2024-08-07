package com.example.java_project.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
 
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Slf4j
@Order(1)

public class TransactionLoggerFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(TransactionLoggerFilter.class);
 
	
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("Initializing the filter {}",this);
	}
 
	public void doFilter(ServletRequest servletrequest, ServletResponse servletresponse, FilterChain filterchain)
			throws IOException, ServletException {
		
		HttpServletRequest request=(HttpServletRequest) servletrequest;
		HttpServletResponse response=(HttpServletResponse) servletresponse;
		logger.info("Starting filter for method {} for the path {}",request.getMethod(),request.getPathInfo());
		
		filterchain.doFilter(servletrequest, servletresponse);
		logger.info("Finished filter for method {} for the path {}",request.getMethod(),request.getPathInfo());
 
	}
	
	 public void destroy() {
			logger.info("Destroyed the filter {}",this);
 
	 }
 
}