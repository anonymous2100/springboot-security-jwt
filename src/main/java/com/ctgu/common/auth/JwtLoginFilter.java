package com.ctgu.common.auth;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alibaba.fastjson.JSON;
import com.ctgu.util.JwtTokenUtil;

/**
 * @ClassName: JwtLoginFilter
 * @Description: 登录拦截器：验证用户名密码正确后 生成一个token并将token返回给客户端
 *               <p>
 *               JWT登录授权过滤器 JWTAuthenticationFilter继承于UsernamePasswordAuthenticationFilter <br/>
 *               该拦截器用于获取用户登录的信息，只需创建一个token并调用authenticationManager.authenticate()<br/>
 *               让spring-security去进行验证就可以了，不用自己查数据库再对比密码了，这一步交给spring去操作。<br/>
 * @author lh2
 * @date 2020年7月2日 下午3:31:15
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter
{
	private AuthenticationManager authenticationManager;

	public JwtLoginFilter(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	/**
	 * 验证操作 接收并解析用户凭证
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException
	{
		// 从输入流中获取到登录的信息
		// 创建一个token并调用authenticationManager.authenticate() 让Spring security进行验证
		return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				request.getParameter("username"), request.getParameter("password")));
	}

	/**
	 * 验证【成功】后调用的方法 * 若验证成功 生成token并返回
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException
	{
		User user = (User) authResult.getPrincipal();
		// 从User中获取权限信息
		Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
		// 创建Token
		String token = JwtTokenUtil.createToken(user.getUsername(), authorities.toString());
		// 设置编码 防止乱码问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		// 在请求头里返回创建成功的token
		// 设置请求头为带有"Bearer "前缀的token字符串
		response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
		// 处理编码方式 防止中文乱码
		response.setContentType("text/json;charset=utf-8");
		// 将反馈塞到HttpServletResponse中返回给前台
		response.getWriter()
				.write(JSON.toJSONString("登录成功"));
	}

	/**
	 * * 验证【失败】调用的方法
	 */
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException
	{
		String returnData = "";
		// 账号过期
		if (failed instanceof AccountExpiredException)
		{
			returnData = "账号过期";
		}
		// 密码错误
		else if (failed instanceof BadCredentialsException)
		{
			returnData = "密码错误";
		}
		// 密码过期
		else if (failed instanceof CredentialsExpiredException)
		{
			returnData = "密码过期";
		}
		// 账号不可用
		else if (failed instanceof DisabledException)
		{
			returnData = "账号不可用";
		}
		// 账号锁定
		else if (failed instanceof LockedException)
		{
			returnData = "账号锁定";
		}
		// 用户不存在
		else if (failed instanceof InternalAuthenticationServiceException)
		{
			returnData = "用户不存在";
		}
		// 其他错误
		else
		{
			returnData = "未知异常";
		}
		// 处理编码方式 防止中文乱码
		response.setContentType("text/json;charset=utf-8");
		// 将反馈塞到HttpServletResponse中返回给前台
		response.getWriter()
				.write(JSON.toJSONString(returnData));
	}
}