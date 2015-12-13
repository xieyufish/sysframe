/**
 * 
 */
package com.shell.common.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.shell.common.exception.DataAccessException;

/**
 * @author shell
 *
 */
public interface BaseDao<T extends Serializable> {
	/**
	 * 插入实体对象，新增一条记录
	 * @param t 具体的对象
	 * @return 返回生成的主键
	 */
	public long add(T t) throws DataAccessException;
	
	/**
	 * 插入多个实体对象，批量插入
	 * @param tList 对象的List集合
	 * @return 返回插入的数据条数
	 */
	public int addBatch(List<T> tList) throws DataAccessException;
	
	/**
	 * 插入多个实体对象，批量插入
	 * @param tArray 对象数组
	 * @return
	 * @see addBatch(List<T> tList)
	 */
	public int addBatch(T[] tArray) throws DataAccessException;
	
	/**
	 * 根据主键删除记录，物理删除
	 * @param key 想要删除的记录
	 * @return 删除记录的条数
	 */
	public int delete(long key) throws DataAccessException;
	
	/**
	 * 根据主键批量删除，物理删除
	 * @param keys  主键的集合
	 * @return 删除记录的条数
	 */
	public int deleteBatch(List<Long> keys) throws DataAccessException;
	
	/**
	 * 根据主键批量删除，物理删除
	 * @param keys 主键数组
	 * @return 删除记录的条数
	 */
	public int deleteBatch(Long[] keys) throws DataAccessException;
	
	/**
	 * 更新对象
	 * @param t 要被更新的对象
	 * @return 更新记录的条数
	 */
	public int update(T t) throws DataAccessException;
	
	/**
	 * 根据主键获取对象
	 * @param key 要获取对象的主键
	 * @return 对象集合
	 */
	public T get(long key) throws DataAccessException;
	
	/**
	 * 根据多个主键，获取对象集合
	 * @param keys 主键集合
	 * @return 对象集合
	 */
	public List<T> getBatch(List<Long> keys) throws DataAccessException;
	
	/**
	 * 根据主键数组，获取对象集合
	 * @param keys
	 * @return 对象集合
	 */
	public List<T> getBatch(Long[] keys) throws DataAccessException;
	
	/**
	 * 获取记录总数
	 * @return 返回记录总数
	 */
	public int count() throws DataAccessException;
	
	/**
	 * 执行sql
	 * @param sql 要执行的sql语句
	 * @param e 传入想到返回的对象类型
	 * @return 返回集合
	 */
	public <E> List<E> executeSql(String sql, E e) throws DataAccessException;
	
	/**
	 * 根据传入的id,执行命名空间下的对应id的语句
	 * @param sqlId
	 * @return
	 * @throws DataAccessException
	 */
	public Object executeBySqlId(String sqlId) throws DataAccessException;
	
	/**
	 * 传入参数，执行配置文件中特定的sql语句
	 * @param params map对象，必须要包含有sqlId属性值，该值指定要执行的sql的id
	 * @return 返回list集合
	 */
	public List<Object> queryList(Map<String, Object> params) throws DataAccessException;
	
	/**
	 * 传入参数，执行配置文件中特定的sql语句
	 * @param params map对象，必须要包含有sqlId属性值，该值指定要执行的sql的id
	 * @return 返回object
	 */
	public Object queryObject(Map<String, Object> params) throws DataAccessException;
}
