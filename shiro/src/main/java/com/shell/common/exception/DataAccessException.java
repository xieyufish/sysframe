/**
 * 
 */
package com.shell.common.exception;

/**
 * @author shell
 * 数据库访问异常
 */
public class DataAccessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2247806788556193043L;
	
	public DataAccessException(RuntimeException e) {
		super(e);
	}
	
	public DataAccessException(String message) {
		super(message);
	}
	
	public DataAccessException(String message, RuntimeException e) {
		super(message, e);
	}

}
