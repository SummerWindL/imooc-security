package com.imooc.security.browser.support;
/**
 *@date 2018年6月12日-下午11:58:24
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class SimpleResponse {

	public SimpleResponse(Object content) {
		this.content = content;
	}
	
	private Object content;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
	
}
