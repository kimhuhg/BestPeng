package com.best.peng.sys.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * 菜单、按钮权限表
 * @author zhoupeng
 *
 */
@Entity
@Table
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false,updatable=false)
	private Date createDate;
	
	@Column(insertable=false)
	private Date modifiedDate;
	
	@Column(nullable=false,columnDefinition="bit default 1")
	private boolean valid;//是否启用
	
	@Column(nullable=false,columnDefinition="bit default 1")
	private boolean visible;//是否可见
	
	@Column(nullable=false,length=20)
	private String menuName;
	
	@Column(nullable=false,length=50,columnDefinition="varchar(50) default '#'")
	private String menuLink;
	
	@Column(length=50)
	private String permissionCode;//授权标识
	
	@Column(nullable=false)
	private Integer permissionType;//菜单权限类型：1-菜单权限，2-操作权限
	
	@Column(nullable=false,columnDefinition="int(4) default 0")
	private Integer parentId;
	
	@Column(nullable=false,length=20)
	private String iconName;
	
	@Column(nullable=false,columnDefinition="int(4) default 10")
	private Integer sortNo;
	
	@Transient
	private String parentName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public Integer getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	
}
