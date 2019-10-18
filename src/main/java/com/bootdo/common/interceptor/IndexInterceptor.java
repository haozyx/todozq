package com.bootdo.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.Commons;

 
/**
 * 拦截前台请求提前set页面所需值
 * @author Administrator
 *
 */
@Component
public class IndexInterceptor implements HandlerInterceptor {
 
	@Autowired
	private Commons commons;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		String requestType = request.getHeader("X-Requested-With");
		//非ajax请求
		if(!"XMLHttpRequest".equals(requestType)){
			//公共包
			request.setAttribute("commons", commons);
			// 设置项
			request.setAttribute("options", Constant.OPTIONS);
			// 文章分类
			request.setAttribute("categories", Constant.CATEGORIES);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
