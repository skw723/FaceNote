package com.nhncorp.facenote;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//TODO referer 처리(상황마다 다름)
		InterceptorCheck check = ((HandlerMethod) handler).getMethodAnnotation(InterceptorCheck.class);
		
		if(check == null) {
			return super.preHandle(request, response, handler);
		}
		
		HttpSession session = request.getSession(false);
		if(check.session()) {
			if(session == null) {
				response.sendRedirect("login.nhn");
				return false;
			}
			String user = (String) request.getSession().getAttribute("user");
			if(StringUtils.isEmpty(user)) {
				response.sendRedirect("login.nhn");
				return false;
			}
		}
		
		return super.preHandle(request, response, handler);
	}
}
