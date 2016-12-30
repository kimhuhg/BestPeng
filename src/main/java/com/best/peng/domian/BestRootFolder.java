package com.best.peng.domian;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 根文件夹
 * @author zhoupeng
 *
 */
@Entity
@Table
public class BestRootFolder {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long rootFolderId;
	
	@Column(nullable=false)
	private Long userId;
	
	@Column(nullable=false,updatable=false)
	private Date createDate;
	
	public Long getRootFolderId() {
		return rootFolderId;
	}
	public void setRootFolderId(Long rootFolderId) {
		this.rootFolderId = rootFolderId;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
