package com.best.peng.domian;

import java.util.Date;

import javax.persistence.*;
/**
 * 文件夹
 * @author zhoupeng
 *
 */
@Entity
@Table
public class BestFolder {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long folderId;
	
	@Column(nullable=false,length=50)
	private String name;
	
	@Column(nullable=false)
	private Long parentId;
	
	@Column(nullable=false,updatable=false)
	private Date createDate;
	
	@Column(insertable=false)
	private Date updateDate;
	
	@Column(nullable=false)
	private Long rootFoldeId;
	
	@Column()
	private Integer folderType;
	
	@Column(nullable=false)
	private boolean valid;
	
	public Integer getFolderType() {
		return folderType;
	}
	public void setFolderType(Integer folderType) {
		this.folderType = folderType;
	}
	public Long getRootFoldeId() {
		return rootFoldeId;
	}
	public void setRootFoldeId(Long rootFoldeId) {
		this.rootFoldeId = rootFoldeId;
	}
	public Long getFolderId() {
		return folderId;
	}
	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
}
