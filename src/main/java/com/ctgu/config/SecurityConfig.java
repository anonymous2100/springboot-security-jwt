package com.ctgu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ctgu.common.auth.JwtAuthenticationEntryPoint;
import com.ctgu.common.auth.JwtAuthorizationFilter;
import com.ctgu.common.auth.JwtLoginFilter;

/**
 * @ClassName: SecurityConfig
 * @Description: Spring Security的配置
 * @author lh2
 * @date 2020年7月2日 上午9:44:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private JwtAuthenticationEntryPoint restAuthenticationEntryPoint;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * 安全配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.cors() // 跨域共享
				.and()
				.csrf()
				.disable() // 使用jwt，不需要跨域，跨域伪造请求限制无效
				// 设置Session的创建策略为：Spring Security永不创建HttpSession 不使用HttpSession来获取SecurityContext
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/*.html", "favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js",
						"/swagger-resources/**", "/v2/api-docs/**")
				.permitAll() // 允许对于网站静态资源的无授权访问
				.and()
				.authorizeRequests()
				// 访问/data需要ADMIN角色
				.antMatchers("/test")
				.hasRole("ADMIN")
				// 其余资源任何人都可访问
				.anyRequest()
				.permitAll()
				.antMatchers(HttpMethod.OPTIONS)
				.permitAll() // 跨域请求会先进行options访问
				.anyRequest()
				.authenticated() // 除了上面的例外之外，全部都要鉴权访问
				.and()
				// 添加JWT登录拦截器
				.addFilter(new JwtLoginFilter(authenticationManager()))
				// 添加JWT鉴权拦截器
				.addFilter(new JwtAuthorizationFilter(authenticationManager()))
				// 异常处理
				.exceptionHandling()
				// 匿名用户访问无权限资源时的异常
				.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
	}

//	// 装载BCrypt密码编码器
//	@Bean
//	public PasswordEncoder passwordEncoder()
//	{
//		return new BCryptPasswordEncoder();
//	}

	/**
	 * 跨域配置
	 * 
	 * @return 基于URL的跨域配置信息
	 */
	@Bean
	public CorsConfigurationSource corsConfigurationSource()
	{
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// 注册跨域配置
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
