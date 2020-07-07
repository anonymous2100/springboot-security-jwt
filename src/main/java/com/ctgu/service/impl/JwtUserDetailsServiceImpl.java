package com.ctgu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ctgu.mapper.RoleMapper;
import com.ctgu.mapper.UserMapper;
import com.ctgu.model.Role;
import com.ctgu.model.User;

/**
 * @ClassName: JwtUserDetailsService
 * @Description: 配置UserDetailsService的实现类 用于加载用户信息
 * @author lh2
 * @date 2020年7月2日 下午4:40:35
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		if (username == null || "".equals(username))
		{
			throw new RuntimeException("用户不能为空");
		}
		// 调用方法查询用户
		User user = userMapper.findByUsername(username);
		if (user == null)
		{
			throw new RuntimeException("用户不存在");
		}
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roleMapper.findRoleByUsername(username))
		{
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), "{noop}" + user.getPassword(),
				authorities);
	}
}