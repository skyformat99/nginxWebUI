package com.cym.config;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AdminInterceptor implements HandlerInterceptor {

	/*
	 * 视图渲染之后的操作
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {

	}

	/*
	 * 处理请求完成后视图渲染之前的处理操作
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {

	}

	/*
	 * 进入controller层之前拦截请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		String ctx = getIP(request.getRequestURL().toString() + "/");

		if (request.getRequestURL().toString().contains("login")) {
			return true;
		}

		Boolean isLogin = (Boolean) request.getSession().getAttribute("isLogin");
		if (isLogin == null || !isLogin) {
			response.sendRedirect(ctx + "/adminPage/login");
			return false;
		}



		return true;
	}

	private static String getIP(String url) {
		URI effectiveURI = null;

		try {
			URI uri = new URI(url);
			effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
		} catch (Throwable var4) {
			effectiveURI = null;
		}
		return effectiveURI.toString();
	}

}