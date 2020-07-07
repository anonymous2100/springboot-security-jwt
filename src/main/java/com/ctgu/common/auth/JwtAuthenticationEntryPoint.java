package com.ctgu.common.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ctgu.common.api.CommonResult;

import cn.hutool.json.JSONUtil;

/**
 * @ClassName: RestAuthenticationEntryPoint
 * @Description:用于进行匿名用户访问资源时无权限的处理
 * @author lh2
 * @date 2020年7月2日 下午3:31:30
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint
{
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		response.getWriter()
				.println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
		response.getWriter()
				.flush();
	}
}