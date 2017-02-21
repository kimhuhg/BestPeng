package com.best.peng.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 基础Mapper
 * @author zhoupeng
 *
 */
@Mapper
public interface BaseMapper<T> {
	//保存对象
	void save(T t);
	
	void save(Map<String, Object> map);
	
	void saveBatch(List<T> list);
	
	//更新对象
	int update(T t);
	
	int update(Map<String, Object> map);
	
	//删除数据
	int delete(Object id);
	
	int delete(Map<String, Object> map);
	
	//批量删除
	int deleteBatch(Object[] id);

	//获取单个对象
	T getObject(Object id);
	
	//分页查询列表
	List<T> list(Map<String, Object> map);
	
	List<T> queryList(Object id);
	
	int queryTotal(Map<String, Object> map);

	//获取记录总条数
	int total();
}
