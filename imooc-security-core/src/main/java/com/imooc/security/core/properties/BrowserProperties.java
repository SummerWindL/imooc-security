package com.imooc.security.core.properties;
/**
 *@date 2018年6月13日-上午12:04:54
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class BrowserProperties {

	
	private String loginPage = "/imooc-signIn.html";

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
