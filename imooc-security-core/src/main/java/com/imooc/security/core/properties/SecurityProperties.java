package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *@date 2018年6月13日-上午12:04:41
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
@ConfigurationProperties(prefix ="imooc.security")
public class SecurityProperties {
	BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
}
