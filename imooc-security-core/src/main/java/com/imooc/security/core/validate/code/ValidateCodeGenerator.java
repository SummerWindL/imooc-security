package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 *@date 2018年7月14日-下午3:33:17
 *@author fu yanliang
 *@action(作用)
 *@instruction
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
}
