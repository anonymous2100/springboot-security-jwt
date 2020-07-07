package com.ctgu.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.ctgu.service.RedisService;

/**
 * @ClassName: RedisServiceImpl
 * @Description: redis操作Service的实现类
 * @author lh2
 * @date 2020年7月2日 下午4:45:58
 */
@Service
public class RedisServiceImpl implements RedisService
{
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public void set(String key, String value)
	{
		stringRedisTemplate.opsForValue()
				.set(key, value);
	}

	@Override
	public String get(String key)
	{
		return stringRedisTemplate.opsForValue()
				.get(key);
	}

	@Override
	public boolean expire(String key, long expire)
	{
		return stringRedisTemplate.expire(key, expire, TimeUnit.SECONDS);
	}

	@Override
	public void remove(String key)
	{
		stringRedisTemplate.delete(key);
	}

	@Override
	public Long increment(String key, long delta)
	{
		return stringRedisTemplate.opsForValue()
				.increment(key, delta);
	}
}
