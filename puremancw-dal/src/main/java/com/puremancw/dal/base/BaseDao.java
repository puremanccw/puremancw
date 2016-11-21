package com.puremancw.dal.base;

import java.util.List;
import java.util.Map;

import com.puremancw.dal.page.PageBean;
import com.puremancw.dal.page.PageParam;

/**
 * 数据访问层基础接口
 * <p>Tilte: BaseDao</p>
 * @author puremancw
 * @date 2016年11月18日 下午3:07:45
 
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 函数功能：单条插入数据。
	 * 
	 * @param entity
	 * @return
	 */
	int insert(T entity);
	
	/**
	 * 函数功能：批量插入数据
	 * 
	 * @param list
	 * @return
	 */
	int insert(List<T> list);
	
	/**
	 * 
	 * 函数功能：根据id单条更新数据
	 * 
	 * @param entity
	 * @return
	 */
	int update(T entity);
	
	/**
	 * 
	 * 函数功能：根据Id批量更新数据
	 * 
	 * @param list
	 * @return
	 */
	int update(List<T> list);
	
	/**
	 * 
	 * 函数功能：根据条件批量更新数据
	 * 
	 * @param paramMap
	 * @return
	 */
	int update(Map<String, Object> paramMap);
	
	/**
	 * 
	 * 函数功能：根据id查询数据
	 * 
	 * @param id
	 * @return
	 */
	T getById(String id);
	
	/**
	 * 
	 * 函数功能：根据column查询数据
	 * 
	 * @param paramMap
	 * @return
	 */
	T getByColumn(Map<String, Object> paramMap);
	
	/**
	 * 
	 * 函数功能：根据column查询列表数据
	 * 
	 * @param paramMap
	 * @return
	 */
	List<T> listByColumn(Map<String, Object> paramMap);
	
	/**
	 * 
	 * 函数功能：根据column查询记录数
	 * 
	 * @param paramMap
	 * @return
	 */
	Long getCountByColumn(Map<String, Object> paramMap);
	
	/**
	 * 
	 * 函数功能：根据id删除数据
	 * 
	 * @param id
	 * @return
	 */
	int delete(String id);
	
	/**
	 * 
	 * 函数功能：根据id批量删除数据
	 * 
	 * @param list
	 * @return
	 */
	int delete(List<T> list);
	
	/**
	 * 
	 * 函数功能：根据column批量删除数据
	 * 
	 * @param paramMap
	 * @return
	 */
	int delete(Map<String, Object> paramMap);
	
	PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);
}
