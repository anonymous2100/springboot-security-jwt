package com.ctgu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ctgu.model.Role;

/**
 * @ClassName: RoleMapper
 * @Description:
 * @author lh2
 * @date 2020年7月2日 下午7:26:08
 */
@Mapper
public interface RoleMapper
{
	int deleteByPrimaryKey(Integer id);

	int insert(Role record);

	Role selectByPrimaryKey(Integer id);

	List<Role> selectAll();

	int updateByPrimaryKey(Role record);

	// 自定义方法
	List<Role> findRoleByUsername(String username);
}