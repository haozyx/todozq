package com.bootdo.system.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.SpringUtil;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.TokenManager;

 

/**
 * 退出<br>
 * web退出和restful方式退出<br>
 * 后者会删除缓存的token
 * 
 */
public class LogoutFilter extends org.apache.shiro.web.filter.authc.LogoutFilter {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		String loginToken = RestfulFilter.getToken(request);
		UserDO user = ShiroUtils.getUser();
		if (StringUtils.isBlank(loginToken)) {// 非Restful方式
			boolean flag = super.preHandle(request, response);
			log.debug("{}退出成功", user.getUsername());
			
			ShiroUtils.logout();//注销

			return flag;
		} else {
			TokenManager tokenManager = SpringUtil.getBean(TokenManager.class);
			boolean flag = tokenManager.deleteToken(loginToken);
			if (flag) {
				RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.OK.value(), SUCCESS_INFO);
				log.debug("{}退出成功", user.getUsername());
			} else {
				RestfulFilter.writeResponse(WebUtils.toHttp(response), HttpStatus.BAD_REQUEST.value(), ERR_INFO);
			}

			return false;
		}
	}

	private static String SUCCESS_INFO = JSONObject.toJSONString(R.ok(HttpStatus.OK.value() + "", "退出成功"));
	private static String ERR_INFO = JSONObject
			.toJSONString(R.ok(HttpStatus.BAD_REQUEST.value() + "", "退出失败,token不存在"));

}
