package com.imooc.security.core.validate.code.sms;
/**
 *@date 2018年7月14日-下午5:06:20
 *@author fu yanliang
 *@action(作用) :短信验证码发送接口
 *@instruction
 */
public interface SmsCodeSender {

	void send(String mobile,String code);
}
