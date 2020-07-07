package com.ctgu.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: Admin
 * @Description:用户实体类
 * @author lh2
 * @date 2020年7月2日 下午5:25:42
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable
{
	/** serialVersionUID */
	private static final long serialVersionUID = -8259450050620191891L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 
	 */
	private String username;

	/**
	 * 
	 */
	private String password;

	/**
	 * 头像
	 */
	private String icon;

	/**
	 * 邮箱
	 */
	private String email;

	/**
	 * 昵称
	 */
	private String nickName;

	/**
	 * 备注信息
	 */
	private String note;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 最后登录时间
	 */
	private Date loginTime;

	/**
	 * 帐号启用状态：0->禁用；1->启用
	 */
	private Integer status;

}