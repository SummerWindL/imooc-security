package com.imooc.security.browser;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;

/**
 *@date 2018年6月12日-下午11:40:06
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@RestController
public class BrowserSecurityController {

	private Logger logger = LoggerFactory.getLogger(BrowserSecurityController.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();//spring security会将当前的请求缓存到session中
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();//跳转的工具
	
	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * 当需要身份认证时跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/authentication/require")
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);//拿到引发跳转的请求
		if(savedRequest!=null) {
			String targetUrl = savedRequest.getRedirectUrl();
			logger.info("引发跳转的请求是："+targetUrl);
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {//判断url是否以.html结尾
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
			}
		}
		return new SimpleResponse("访问的服务需要身份认证，请引导用户到登陆页");
	}
}
