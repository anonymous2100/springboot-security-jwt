package com.ctgu.service;

/**
 * @ClassName: RedisService
 * @Description: redis操作Service, 对象和数组都以json形式进行存储
 * @author lh2
 * @date 2020年7月2日 下午4:46:30
 */
public interface RedisService
{
	/**
	 * 存储数据
	 */
	void set(String key, String value);

	/**
	 * 获取数据
	 */
	String get(String key);

	/**
	 * 设置超期时间
	 */
	boolean expire(String key, long expire);

	/**
	 * 删除数据
	 */
	void remove(String key);

	/**
	 * 自增操作
	 * 
	 * @param delta
	 *            自增步长
	 */
	Long increment(String key, long delta);

}
