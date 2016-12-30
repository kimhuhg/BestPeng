package com.best.peng.domian;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;

@Entity
@Table
public class BestUser implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@Email(message="不是正确的邮箱格式")
	@Column(length=50,nullable=false,unique=true)
	private String email;
	
	@Column(unique=true)
	private Long phoneNumber;
	
	@Column(nullable=false,length=50)
	private String password;
	
	@Column(length=50)
	private String userName;
	
	//设置默认值为1
	@Column(nullable=false,columnDefinition="bit default 1")
	private boolean valid;
	
	//指定长度和设置默认值
	@Column(nullable=false,columnDefinition="int(4) default 0")
	private Integer status;
	
	@Column(nullable=false,updatable=false)
	private Date createDate;
	
	@Column(insertable=false)
	private Date updateDate;
	
	@Column()
	private Date loginDate;
	
	@Column()
	private Date updatePwd;
	
	@Column()
	private Long qq;
	
	@Column(length=100)
	private String avatarUrl;
	
	@Column(columnDefinition="int(4)")
	private Integer groupId;
	
	@Column(columnDefinition="int(4)")
	private Integer companyId;

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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public Date getUpdatePwd() {
		return updatePwd;
	}

	public void setUpdatePwd(Date updatePwd) {
		this.updatePwd = updatePwd;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getQq() {
		return qq;
	}

	public void setQq(Long qq) {
		this.qq = qq;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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

	public Integer isStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Integer getStatus() {
		return status;
	}
	
	
	
}
