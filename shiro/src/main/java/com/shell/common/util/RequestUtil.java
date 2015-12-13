/*
 * 
 */
package com.shell.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shell
 *
 */
public class RequestUtil {
	
	/**
	 * 用于判断用户请求是否是ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest request) {
        boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        String ajaxFlag = null == request.getParameter("ajax") ? "false" : request.getParameter("ajax");
        boolean isAjax = ajax || ajaxFlag.equalsIgnoreCase("true");
        return isAjax;
    }
}
