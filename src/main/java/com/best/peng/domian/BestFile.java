package com.best.peng.domian;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table
public class BestFile {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long fileId;
	
	@Column(nullable=false,length=50)
	private String name;
	
	@Column(nullable=false)
	private Integer size;
	
	@Column(nullable=false,length=200)
	private String url;
	
	@Column(nullable=false,updatable=false)
	private Date createTime;
	
	@Column(insertable=false)
	private Date updateTime;
	
	@Column(nullable=false)
	private Long parentId;
	
	@Column(nullable=false,length=50)
	private String eTag;
	
	@Column(nullable=false)
	private Long rootFoldeId;
	
	@Column(nullable=false,columnDefinition="bit default 1")
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
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getRootFoldeId() {
		return rootFoldeId;
	}
	public void setRootFoldeId(Long rootFoldeId) {
		this.rootFoldeId = rootFoldeId;
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
	public String getETag() {
		return eTag;
	}
	public void setETag(String eTag) {
		this.eTag = eTag;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
