package com.best.peng.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.best.peng.sys.entity.BaseModule;

@Mapper
public interface BaseModuleMapper extends BaseMapper<BaseModule>{
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(BaseModule record);

    int insertSelective(BaseModule record);

    BaseModule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseModule record);

    int updateByPrimaryKey(BaseModule record);
    
    @Select("select m.*,(select p.name from base_module p where p.id = m.parent_id) as parentName from base_module m")
    List<BaseModule> list2(Map<String, Object> map);
    
}