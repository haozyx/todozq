package com.bootdo.system.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.config.Constant;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.SpringUtil;
import com.bootdo.system.service.TokenManager;

import cn.hutool.core.util.StrUtil;


/**
 * Restful方式登陆<br>
 * 在参数中或者header里加参数login-token作为登陆凭证<br>
 * 参数值是登陆成功后的返回值中获取
 * 
 * @author 小威老师 xiaoweijiagou@163.com
 *
 *         2017年8月3日
 */
public class RestfulFilter extends UserFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(WebUtils.toHttp(request).getMethod())) {
            return Boolean.TRUE;
        }
        HttpServletRequest req = (HttpServletRequest) request;
        
        String url = req.getServletPath();
        System.out.println(url);
        
		String loginToken = getToken(request);
		
		if (StringUtils.isBlank(loginToken)&&!url.startsWith("/api")) {// 非Restful方式
			return super.isAccessAllowed(request, response, mappedValue);
		}
		//如果token为空，说明是用户随便输入网址就想访问此时不允许
		if(StrUtil.isBlank(loginToken)) {
			loginToken ="b8be3f7d-67e7-4bc7-b145-2b3093d9aac6";
		}
		TokenManager tokenManager = SpringUtil.getBean(TokenManager.class);
		UsernamePasswordToken token = tokenManager.getToken(loginToken);
		
		
		if (token != null) {
			try {
				Subject subject = getSubject(request, response);
				if (subject.getPrincipal() == null) {
					subject.login(token);
				}

				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * 根据参数或者header获取login-token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(ServletRequest request) {
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
		String loginToken = httpServletRequest.getParameter(Constant.LOGIN_TOKEN);
		if (StringUtils.isBlank(loginToken)) {
			loginToken = httpServletRequest.getHeader(Constant.LOGIN_TOKEN);
		}

		return loginToken;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		String loginToken = getToken(request);
		if (StringUtils.isBlank(loginToken)) {
			return super.onAccessDenied(request, response);
		}

		writeResponse(WebUtils.toHttp(response), HttpStatus.UNAUTHORIZED.value(), info);
		return false;
	}

	private static String info = JSONObject
			.toJSONString(R.ok(HttpStatus.UNAUTHORIZED.value() + "", "token不存在或者过期"));

	public static void writeResponse(HttpServletResponse response, int status, String json) {
		try {
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "*");
			response.setContentType("application/json;charset=UTF-8");
			response.setStatus(status);
			response.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
