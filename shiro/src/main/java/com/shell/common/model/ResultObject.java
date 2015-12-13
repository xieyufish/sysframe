/*
 * 
 */
package com.shell.common.model;

import com.shell.common.enums.BackCodeEnum;
import com.shell.common.util.JsonUtil;

/**
 * @author shell
 * JSON请求处理返回对象
 */
public class ResultObject {
	/**
	 * 返回数据
	 */
	private Object data;

	/**
	 * 返回码
	 */
	private int backCode;

	/**
	 * 返回信息描述
	 */
	private String msg;

	public ResultObject() {

	}

	public ResultObject(int backCode, String msg) {
		this.setBackCode(backCode);
		this.setMsg(msg);
	}

	public ResultObject(BackCodeEnum backCode) {
		this.setBackCode(backCode.getValue());
		this.setMsg(backCode.getDesc());
	}

	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the backCode
	 */
	public int getBackCode() {
		return backCode;
	}

	/**
	 * @param backCode
	 *            the backCode to set
	 */
	public void setBackCode(int backCode) {
		this.backCode = backCode;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return this.data;
	}

	@Override
	public String toString() {
		return JsonUtil.toJSONString(this);
	}

}
