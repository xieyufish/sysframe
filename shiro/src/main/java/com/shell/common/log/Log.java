/*
 * 
 */
package com.shell.common.log;

import java.io.Serializable;

/**
 * @author shell
 *
 */
public class Log  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1180258249150926744L;
	
	private int userName;
	private String operateAddr;
	private long operateTime;
	private String operate;

	/**
	 * @return the userName
	 */
	public int getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(int userName) {
		this.userName = userName;
	}

	/**
	 * @return the operateAddr
	 */
	public String getOperateAddr() {
		return operateAddr;
	}

	/**
	 * @param operateAddr
	 *            the operateAddr to set
	 */
	public void setOperateAddr(String operateAddr) {
		this.operateAddr = operateAddr;
	}

	/**
	 * @return the operateTime
	 */
	public long getOperateTime() {
		return operateTime;
	}

	/**
	 * @param operateTime
	 *            the operateTime to set
	 */
	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}

	/**
	 * @return the operate
	 */
	public String getOperate() {
		return operate;
	}

	/**
	 * @param operate
	 *            the operate to set
	 */
	public void setOperate(String operate) {
		this.operate = operate;
	}

}
