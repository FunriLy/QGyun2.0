package com.qg.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 
 * @author fangrui
 * <p>
 * 字符编码过滤器
 * </p>
 */

public class EncodingFilterUtil implements  Filter{

	private String ENCODING;
	
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain filterChain) throws IOException, ServletException {
		//字符编码设置为UTF-8
		servletRequest.setCharacterEncoding("utf-8");
		servletResponse.setCharacterEncoding(ENCODING);
		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//从xml中初始化ENCODING
		ENCODING = filterConfig.getInitParameter("ENCODING");
	}
	
}
