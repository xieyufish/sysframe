/*
 * 
 */
package com.shell.basemodel.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shell.basemodel.BaseModel;
import com.shell.basemodel.ifc.BaseModelDao;
import com.shell.basemodel.ifc.BaseModelService;
import com.shell.common.dao.BaseDao;
import com.shell.common.service.impl.BaseServiceImpl;

/**
 * @author shell
 *
 */
@Service
public class BaseModelServiceImpl extends BaseServiceImpl<BaseModel> implements BaseModelService {
	
	@Autowired
	private BaseModelDao dao;

	@Override
	protected BaseDao<BaseModel> getDao() {
		return dao;
	}

	@Override
	public void test() {
		System.out.println("test........");
	}

}
