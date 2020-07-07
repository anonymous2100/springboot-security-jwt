package com.ctgu.common.auth;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @ClassName: AdminUserDetails
 * @Description: 自定义SpringSecurity需要的用户详情
 * @author lh2
 * @date 2020年7月2日 下午4:43:02
 */
public class MyUserDetails implements UserDetails, Serializable
{
	/** serialVersionUID */
	private static final long serialVersionUID = -2280367569104053031L;

	private Integer id;
	private String username;
	private String password;
	private Set<? extends GrantedAuthority> authorities;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		return this.authorities;
	}

	public void setAuthorities(Set<? extends GrantedAuthority> authorities)
	{
		this.authorities = authorities;
	}

	@Override
	public String getPassword()
	{
		return this.password;
	}

	@Override
	public String getUsername()
	{
		return this.username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	// 账号是否过期
	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}

	// 账号是否锁定
	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	// 账号凭证是否未过期
	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled()
	{
		return true;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}
}