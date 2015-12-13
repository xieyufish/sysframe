package com.shell.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页，欢迎页配置
 * @author shell
 *
 */
@Controller
@Scope("prototype")
public class IndexController {
	
	@RequestMapping({"/","/index"})
	public String index() {
		return "jsp/index";
	}
	
}
