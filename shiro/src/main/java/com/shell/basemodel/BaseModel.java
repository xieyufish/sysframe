package com.shell.basemodel;

import java.io.Serializable;

/**
 * 基本实体类，包含每个实体都会拥有的属性
 * @author shell
 *
 */
public class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6871545866706831843L;

	private long id;
	private long createTime;
	private long lastModifyTime;
	private int status;

	public long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
