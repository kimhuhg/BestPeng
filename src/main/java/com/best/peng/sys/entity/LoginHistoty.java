package com.best.peng.sys.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table
public class LoginHistoty {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false)
	private Integer userId;
	
	@Column(nullable=false,length=20)
	private String clientIp;
	
	@Column(nullable=false)
	private Date loginDate;
	
	@Column(nullable=false,length=20)
	private String loginAdvice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginAdvice() {
		return loginAdvice;
	}

	public void setLoginAdvice(String loginAdvice) {
		this.loginAdvice = loginAdvice;
	}
	
	
}
