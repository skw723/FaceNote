package com.nhncorp.facenote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter{
	private final String excludePath = "/viewpost"; 

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String referer = request.getHeader("Referer");
		String url = request.getRequestURI();
		
		if(StringUtils.startsWith(url, excludePath)) {
			return super.preHandle(request, response, handler);
		}
		if(referer == null) {
			response.sendRedirect("/");
			return false;
		}
		return super.preHandle(request, response, handler);
	}
}
