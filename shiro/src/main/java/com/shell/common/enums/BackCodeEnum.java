/*
 * 
 */
package com.shell.common.enums;

/**
 * @author shell
 * 对请求返回码的定义和描述
 */
public enum BackCodeEnum {
	SUCCESS(200, "成功"),
	DATABASE_ERROR(400, "数据库访问错误"),
	SYSTEME_RROR(500,"系统错误");
	private int value;
	private String desc;
	
	private BackCodeEnum(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String getDesc() {
		return this.desc;
	}
}
