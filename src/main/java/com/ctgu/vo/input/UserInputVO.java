package com.ctgu.vo.input;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: UserInputVO
 * @Description: 输入参数
 * @author lh2
 * @date 2020年7月2日 下午6:55:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInputVO
{
	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
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

}
