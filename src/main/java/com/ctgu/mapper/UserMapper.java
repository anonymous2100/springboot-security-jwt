package com.ctgu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ctgu.model.Role;
import com.ctgu.model.User;

/**
 * @ClassName: UserMapper
 * @Description:
 * @author lh2
 * @date 2020年7月2日 下午5:24:23
 */
@Mapper
public interface UserMapper
{
	int deleteByPrimaryKey(Long id);

	int insert(User record);

	User selectByPrimaryKey(Long id);

	List<User> selectAll();

	int updateByPrimaryKey(User record);

	User findByUsername(String username);

}