/**
 * 
 */
package com.shell.common.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.shell.basemodel.BaseModel;
import com.shell.common.dao.BaseDao;
import com.shell.common.service.BaseService;

/**
 * @author shell
 *
 */
public abstract class BaseServiceImpl<T extends BaseModel> implements BaseService<T> {
	protected final Logger logger = Logger.getLogger(this.getClass().getName());
	
	protected abstract BaseDao<T> getDao();

	@Override
	public long add(T t) {
		return this.getDao().add(t);
	}

	@Override
	public int addBatch(List<T> tList) {
		return this.getDao().addBatch(tList);
	}

	@Override
	public int addBatch(T[] tArray) {
		return this.getDao().addBatch(tArray);
	}

	@Override
	public int delete(long key) {
		return this.getDao().delete(key);
	}

	@Override
	public int deleteBatch(List<Long> keys) {
		return this.getDao().deleteBatch(keys);
	}

	@Override
	public int deleteBatch(Long[] keys) {
		return this.getDao().deleteBatch(keys);
	}

	@Override
	public int update(T t) {
		return this.getDao().update(t);
	}

	@Override
	public T get(long key) {
		return this.getDao().get(key);
	}

	@Override
	public List<T> getBatch(List<Long> keys) {
		return this.getDao().getBatch(keys);
	}

	@Override
	public List<T> getBatch(Long[] keys) {
		return this.getDao().getBatch(keys);
	}

	@Override
	public int count() {
		return this.getDao().count();
	}

	@Override
	public <E> List<E> executeSql(String sql, E e) {
		return this.getDao().executeSql(sql, e);
	}

	@Override
	public List<Object> queryList(Map<String, Object> params) {
		return this.getDao().queryList(params);
	}

	@Override
	public Object queryObject(Map<String, Object> params) {
		return this.getDao().queryObject(params);
	}

	@Override
	public Object executeBySqlId(String sqlId) {
		return this.getDao().executeBySqlId(sqlId);
	}

}
