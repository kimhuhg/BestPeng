package com.best.peng.sys.entity;

import javax.persistence.*;

/**
 * 系统配置参数
 * @author zhoupeng
 *
 */
@Entity
@Table
public class SysConfig {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false,length=20,unique=true)
	private String name;
	
	@Column(nullable=false,length=20)
	private String value;
	
	@Column(nullable=false,columnDefinition="int(4) default 1")
	private Integer settingType;
	
	@Column(nullable=false,columnDefinition="int(4) default 1")
	private Integer status;
	
	@Column(length=100)
	private String remark;
	
	
}
