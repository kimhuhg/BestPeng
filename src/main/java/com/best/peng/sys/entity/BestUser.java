package com.best.peng.sys.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class BestUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(length=30,nullable=false,unique=true)
	private String email;
		
	@Column(nullable=false,length=50)
	private String password;
	
	@Column(length=30)
	private String userName;
	
	//设置默认值为1
	@Column(nullable=false,columnDefinition="bit default 1")
	private boolean valid;
	
	//指定长度和设置默认值
	@Column(nullable=false,columnDefinition="int(4) default 0")
	private Integer status;
	
	//updatable=false:update的时候不更新
	@Column(nullable=false,updatable=false)
	private Date createDate;
	
	//insertable=false:insert的时候不更新
	@Column(insertable=false)
	private Date modifiedDate;
	
	@Column(nullable=false)
	private Date loginDate;
	
	@Column(nullable=false,columnDefinition="int(4) default 0")
	private Integer groupId;
	
	/**
     * 一个用户对应一个角色
     * 关联实体(User)的主键一般用来做外键。如果不使用主键作为外键，则需要设置referencedColumnName属性
     * 会生成已role_id的外键，并添加生成外键约束
     */
    @ManyToOne()
    @JoinColumn()
    private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
