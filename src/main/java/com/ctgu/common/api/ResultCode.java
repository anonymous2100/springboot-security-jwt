package com.ctgu.common.api;

/**
 * @ClassName: ResultCode
 * @Description: 常用API操作码
 * @author lh2
 * @date 2020年7月2日 下午3:30:58
 */
public enum ResultCode
{
	SUCCESS(200, "操作成功"), //
	FAILED(500, "操作失败"),//
	VALIDATE_FAILED(404, "参数检验失败"),//
	UNAUTHORIZED(401, "您未登录或token已经过期，没有访问权限"),//
	FORBIDDEN(403, "没有相关权限");

	private long code;
	private String message;

	private ResultCode(long code, String message)
	{
		this.code = code;
		this.message = message;
	}

	public long getCode()
	{
		return code;
	}

	public String getMessage()
	{
		return message;
	}
}
