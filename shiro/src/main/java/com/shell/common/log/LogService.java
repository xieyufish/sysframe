/*
 * 
 */
package com.shell.common.log;

import org.springframework.stereotype.Service;

/**
 * @author shell
 *
 */
@Service
public class LogService {
	
	public void addLog(Log log) {
		System.out.println("插入日志到数据库");
	}
}
