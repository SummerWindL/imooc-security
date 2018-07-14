package com.imooc.security.core.properties;
/**
 *@date 2018年7月4日-下午11:05:08
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class ValidateCodeProperties {

	private ImageCodeProperties image = new ImageCodeProperties();

	private SmsCodeProperties smsCode = new SmsCodeProperties();
	
	public ImageCodeProperties getImage() {
		return image;
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(SmsCodeProperties smsCode) {
		this.smsCode = smsCode;
	}
	
}
