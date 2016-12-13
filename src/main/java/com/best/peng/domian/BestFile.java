package com.best.peng.domian;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table
public class BestFile {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long fileId;
	@Column(nullable=false,length=50)
	private String name;
	@Column(nullable=false)
	private Double size;
	@Column(nullable=false,length=200)
	private String path;
	@Column(nullable=false)
	private Date createTime;
	@Column()
	private Date updateTime;
	@Column(nullable=false,length=6)
	private Long parentId;
	@Column(nullable=false,length=50)
	private String md5;
	@Column(nullable=false)
	private boolean valid;
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getSize() {
		return size;
	}
	public void setSize(Double size) {
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
