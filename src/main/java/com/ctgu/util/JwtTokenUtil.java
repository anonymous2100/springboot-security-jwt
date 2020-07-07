package com.ctgu.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: JwtTokenUtil
 * @Description: JwtToken生成的工具类 <br>
 *               JWT token的格式：header.payload.signature<br>
 *               header的格式（算法、token的类型）：<br>
 *               {"alg": "HS512","typ": "JWT"}<br>
 *               payload的格式（用户名、创建时间、生成时间）：<br>
 *               {"sub":"wang","created":1489079981393,"exp":1489684781}<br>
 *               signature的生成算法：<br>
 *               HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload), secret)<br>
 * @author lh2
 * @date 2020年7月2日 上午10:19:05
 */
@Slf4j
public class JwtTokenUtil
{
	// Token请求头
	public static final String TOKEN_HEADER = "Authorization";
	// Token前缀
	public static final String TOKEN_PREFIX = "Bearer ";

	// 签名主题
	public static final String SUBJECT = "mySecret";
	// 过期时间
	public static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;
	// 应用密钥
	public static final String APPSECRET_KEY = "piconjo_secret";
	// 角色权限声明
	private static final String ROLE_CLAIMS = "role";

	/**
	 * 生成Token
	 */
	public static String createToken(String username, String role)
	{
		Map<String, Object> map = new HashMap<>();
		map.put(ROLE_CLAIMS, role);

		String token = Jwts.builder()
				.setSubject(username)
				.setClaims(map)
				.claim("username", username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
				.signWith(SignatureAlgorithm.HS256, APPSECRET_KEY)
				.compact();
		return token;
	}

	/**
	 * 校验Token
	 */
	public static Claims checkJWT(String token)
	{
		try
		{
			final Claims claims = Jwts.parser()
					.setSigningKey(APPSECRET_KEY)
					.parseClaimsJws(token)
					.getBody();
			return claims;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 从Token中获取username
	 */
	public static String getUsername(String token)
	{
		Claims claims = Jwts.parser()
				.setSigningKey(APPSECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.get("username")
				.toString();
	}

	/**
	 * 从Token中获取用户角色
	 */
	public static String getUserRole(String token)
	{
		Claims claims = Jwts.parser()
				.setSigningKey(APPSECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.get("role")
				.toString();
	}

	/**
	 * 校验Token是否过期
	 */
	public static boolean isExpiration(String token)
	{
		Claims claims = Jwts.parser()
				.setSigningKey(APPSECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		return claims.getExpiration()
				.before(new Date());
	}
}
