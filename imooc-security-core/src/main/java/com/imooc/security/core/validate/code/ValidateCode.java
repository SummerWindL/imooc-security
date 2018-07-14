package com.imooc.security.core.validate.code;
/**
 *@date 2018年6月14日-下午8:51:24
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ValidateCode {

	private String code;
	
	private LocalDateTime expireTime;

	public ValidateCode(String code, int expireIn) {
		this.code = code;
		this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
	}
	
	public ValidateCode(String code, LocalDateTime expireTime) {
		this.code = code;
		this.expireTime = expireTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}

	public boolean isExpried() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	
}
