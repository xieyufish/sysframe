/**
 * 
 */
package com.shell.common.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.shell.basemodel.BaseModel;
import com.shell.common.dao.BaseDao;

/**
 * @author shell
 * BaseDao的实现类，基于mybatis框架
 */
public class BaseDaoImpl<T extends BaseModel> implements BaseDao<T> {
	protected final Logger logger = Logger.getLogger(this.getClazz().getName());
	
	@Autowired
	private SqlSession sqlSession;
	
	private Class<T> clazz;
	
	/**
	 * 命名空间： 默认是T的全路径
	 */
	private String namespace;
	
	/**
	 * 表名： 默认是类名
	 */
	private String tableName;
	
	@SuppressWarnings("unchecked")
	protected Class<T> getClazz() {
		if (this.clazz == null) {
			this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return this.clazz;
	}
	
	/**
	 * 获取命名空间，这就限制了在我们配置mybatis的映射文件时，他们的命名空间必须是以bean的全路径命名的
	 * 可以被子类重写
	 * @return
	 */
	protected String getNamespace() {
		if (this.namespace == null) {
			this.namespace = getClazz().getName();
		}
		return this.namespace;
	}
	
	protected String getTableName() {
		if (this.tableName == null) {
			this.tableName = getClazz().getSimpleName();
		}
		return this.tableName;
	}
	
	public SqlSession getSqlSession() {
		return this.sqlSession;
	}
	
	protected void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	/**
	 * 插入实体对象，新增一条记录
	 * @param t 具体的对象
	 * @return 返回生成的主键
	 */
	@Override
	public long add(T t) {
		/** 在里面不直接用this.namespace而是要用方法的原因是：
		 * namespace的取值可能不是对应t的全路径名，可能会被子类重设，所以要用getNamespace方法（多态）
		 */
		sqlSession.insert(getNamespace() + ".add", t);
		return t.getId();
	}
	
	/**
	 * 插入多个实体对象，批量插入
	 * @param tList 对象的List集合
	 * @return 返回插入的数据条数
	 */
	@Override
	public int addBatch(List<T> tList) {
		return sqlSession.insert(getNamespace() + ".addBatch", tList);
	}
	
	/**
	 * 插入多个实体对象，批量插入
	 * @param tArray 对象数组
	 * @return
	 * @see addBatch(List<T> tList)
	 */
	@Override
	public int addBatch(T[] tArray) {
		return addBatch(Arrays.asList(tArray));
	}
	
	/**
	 * 根据主键删除记录，物理删除
	 * @param key 想要删除的记录
	 * @return 删除记录的条数
	 */
	@Override
	public int delete(long key) {
		return sqlSession.delete(getNamespace() + ".delete", key);
	}
	
	/**
	 * 根据主键批量删除，物理删除
	 * @param keys  主键的集合
	 * @return 删除记录的条数
	 */
	@Override
	public int deleteBatch(List<Long> keys) {
		return sqlSession.delete(getNamespace() + ".deleteBatch", keys);
	}
	
	/**
	 * 根据主键批量删除，物理删除
	 * @param keys 主键数组
	 * @return 删除记录的条数
	 */
	@Override
	public int deleteBatch(Long[] keys) {
		return deleteBatch(Arrays.asList(keys));
	}
	
	/**
	 * 更新对象
	 * @param t 要被更新的对象
	 * @return 更新记录的条数
	 */
	@Override
	public int update(T t) {
		return sqlSession.update(getNamespace() + ".update", t);
	}
	
	/**
	 * 根据主键获取对象
	 * @param key 要获取对象的主键
	 * @return 对象集合
	 */
	@Override
	public T get(long key) {
		return sqlSession.selectOne(getNamespace() + ".get", getClazz());
	}
	
	/**
	 * 根据多个主键，获取对象集合
	 * @param keys 主键集合
	 * @return 对象集合
	 */
	@Override
	public List<T> getBatch(List<Long> keys) {
		return sqlSession.selectList(getNamespace() + ".getBatch", getClazz());
	}
	
	/**
	 * 根据主键数组，获取对象集合
	 * @param keys
	 * @return 对象集合
	 */
	@Override
	public List<T> getBatch(Long[] keys) {
		return getBatch(Arrays.asList(keys));
	}
	
	/**
	 * 获取记录总数
	 * @return 返回记录总数
	 */
	@Override
	public int count() {
		return sqlSession.selectOne(getNamespace() + ".count");
	}
	
	/**
	 * 执行sql
	 * @param sql 要执行的sql语句
	 * @param e 传入想到返回的对象类型
	 * @return 返回集合
	 */
	@Override
	public <E> List<E> executeSql(String sql, E e) {
		return sqlSession.selectList(getNamespace() + ".executeSql", e);
	}
	
	/**
	 * 传入参数，执行配置文件中特定的sql语句
	 * @param params map对象，必须要包含有sqlId属性值，该值指定要执行的sql的id
	 * @return 返回list集合
	 */
	@Override
	public List<Object> queryList(Map<String, Object> params) {
		return sqlSession.selectList(getNamespace() + "." + params.get("sqlId"), params);
	}
	
	/**
	 * 传入参数，执行配置文件中特定的sql语句
	 * @param params map对象，必须要包含有sqlId属性值，该值指定要执行的sql的id
	 * @return 返回object
	 */
	@Override
	public Object queryObject(Map<String, Object> params) {
		return sqlSession.selectOne(getNamespace() + "." + params.get("sqlId"), params);
	}

	@Override
	public Object executeBySqlId(String sqlId) {
		return sqlSession.selectList(getNamespace() + "." + sqlId);
	}
}
