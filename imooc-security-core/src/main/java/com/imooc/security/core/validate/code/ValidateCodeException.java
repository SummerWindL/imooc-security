package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 *@date 2018年6月15日-下午5:34:30
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public class ValidateCodeException extends AuthenticationException{

	private static final long serialVersionUID = -7285211528095468156L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
