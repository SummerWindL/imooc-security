package com.imooc.security.core.validate.code.sms;
/**
 *@date 2018年7月14日-下午5:07:49
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println("向手机："+mobile+"发送验证码："+code);
	}

}
