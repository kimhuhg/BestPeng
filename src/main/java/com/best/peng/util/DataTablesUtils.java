package com.best.peng.util;

import java.util.ArrayList;

import org.springframework.data.domain.Page;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.best.peng.sys.entity.DataTable;

/**
 * DataTables 工具类
 * @author zhoupeng
 * 2017-1-7
 */
public class DataTablesUtils {
	
	/**
	 * 将datatables对象转换成json字符串
	 * @param dt
	 * @return
	 */
	public static String getJson(DataTable dt){
		
		return JSON.toJSONString(dt,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * json字符串返回
	 * @param page
	 * @param draw
	 * @return
	 */
	public static String getJson(Page<?> page,Integer draw){
		DataTable dt=new DataTable();
		dt.setDraw(draw);
		if(page!=null){
			dt.setData(page.getContent());
			dt.setRecordsTotal(page.getTotalElements());
			dt.setRecordsFiltered(page.getTotalElements());
		}else{
			dt.setData(new ArrayList<>());
			dt.setRecordsTotal(0);
			dt.setRecordsFiltered(0);
		}
		
		return JSON.toJSONString(dt,SerializerFeature.DisableCircularReferenceDetect);
	}
}
