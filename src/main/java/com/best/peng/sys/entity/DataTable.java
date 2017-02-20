package com.best.peng.sys.entity;

import java.util.List;
/**
 * 服务器需要返回的数据
 * @author zhoupeng
 *2017-1-7
 */
public class DataTable {
	//Datatables发送的draw是多少那么服务器就返回多少。
	private Integer draw;
	//即没有过滤的记录数（数据库里总共记录数）
	private long recordsTotal;
	//过滤后的记录数（如果有接收到前台的过滤条件，则返回的是过滤后的记录数）
	private long recordsFiltered;
	//表中中需要显示的数据。
	private List<?> data;
	//你可以定义一个错误来描述服务器出了问题后的友好提示
	private String error;
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public long getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public long getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<?> getData() {
		return data;
	}
	public void setData(List<?> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
