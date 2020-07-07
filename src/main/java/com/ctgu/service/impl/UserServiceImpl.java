package com.ctgu.service.impl;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ctgu.common.api.CommonResult;
import com.ctgu.mapper.UserMapper;
import com.ctgu.model.User;
import com.ctgu.service.RedisService;
import com.ctgu.service.UserService;
import com.ctgu.vo.input.UserInputVO;

/**
 * @ClassName: UmsMemberServiceImpl
 * @Description:
 * @author lh2
 * @date 2020年7月2日 下午4:46:19
 */
@Service
public class UserServiceImpl implements UserService
{
	@Value("${redis.key.prefix.authCode}")
	private String REDIS_KEY_PREFIX_AUTH_CODE;

	@Value("${redis.key.expire.authCode}")
	private Long AUTH_CODE_EXPIRE_SECONDS;

	@Autowired
	private RedisService redisService;
	@Autowired
	private UserMapper userMapper;
	// @Autowired
	// private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void addUser(UserInputVO userInputVO)
	{
		User user = new User();
		BeanUtils.copyProperties(userInputVO, user);

		// 注册的时候把密码加密一下
		// user.setPassword(bCryptPasswordEncoder.encode(userInputVO.getPassword()));
		userMapper.insert(user);
	}

}
