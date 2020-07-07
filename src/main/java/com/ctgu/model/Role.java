package com.ctgu.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ClassName: Role
 * @Description:
 * @author lh2
 * @date 2020年7月2日 下午6:45:49
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable
{
	/** serialVersionUID */
	private static final long serialVersionUID = -524833989078680616L;

	/**
	 * 
	 */
	private Integer id;

	/**
	 * 
	 */
	private String username;

	/**
	 * 
	 */
	private String name;

}