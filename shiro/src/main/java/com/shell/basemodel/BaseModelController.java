/*
 * 
 */
package com.shell.basemodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shell.basemodel.ifc.BaseModelService;
import com.shell.common.controller.BaseController;
import com.shell.common.model.ResultObject;

/**
 * @author shell
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/model")
public class BaseModelController extends BaseController {
	
	@Autowired
	private BaseModelService service;
	
	@RequestMapping("/test/{hhh}")
	public ResultObject test(@PathVariable("hhh") String hhh ) {
		System.out.println(hhh);
		System.out.println(request.getParameter("name"));
		BaseModel t = new BaseModel();
		t.setId(2334l);
//		service.test();
		Object obj = service.add(t);
		ResultObject resultObject = new ResultObject();
		resultObject.setData(obj);
		return resultObject;
	}
}
