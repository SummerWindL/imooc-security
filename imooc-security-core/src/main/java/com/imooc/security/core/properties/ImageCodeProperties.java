package com.imooc.security.core.properties;
/**
 *@date 2018年7月4日-下午11:03:09
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class ImageCodeProperties extends SmsCodeProperties{

	public ImageCodeProperties() {
		setLength(4);
	}
	
	private int width = 67;
	private int height = 23;
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
