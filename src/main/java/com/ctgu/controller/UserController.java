package com.ctgu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctgu.model.User;
import com.ctgu.service.UserService;
import com.ctgu.vo.input.UserInputVO;

import io.swagger.annotations.Api;

/**
 * @ClassName: UmsAdminController
 * @Description: 后台用户管理
 * @author lh2
 * @date 2020年7月2日 下午4:42:07
 */
@Api(tags = "UserController")
@RestController
public class UserController
{
	@Autowired
	private UserService userService;

	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Value("${jwt.tokenHead}")
	private String tokenHead;

	/**
	 * 注册
	 */
	@PostMapping("/register")
	public String register(@RequestBody UserInputVO userInputVO)
	{
		userService.addUser(userInputVO);
		return "success";
	}

	/**
	 * 测试接口 - 需要相应权限
	 */
	@GetMapping("/test")
	public String data()
	{
		return "success";
	}

}
