/*
 * 
 */
package com.shell.basemodel.impl;

import org.springframework.stereotype.Repository;

import com.shell.basemodel.BaseModel;
import com.shell.basemodel.ifc.BaseModelDao;
import com.shell.common.dao.impl.BaseDaoImpl;

/**
 * @author shell
 *
 */
@Repository
public class BaseModelDaoImpl extends BaseDaoImpl<BaseModel> implements BaseModelDao {

}
