package com.best.peng.sys.entity;

/**
 * API响应消息对象
 * @author zhoupeng
 *
 */
public class ResponseMessage {
	
	/**
	 * 结果代号：0--成功
	 */
	private Integer code;
	//提示消息
	private String msg;
	//错误代码
	private String error;
	//数据集
	private Object data;
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
}
