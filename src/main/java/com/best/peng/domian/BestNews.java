package com.best.peng.domian;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * 新闻信息文章表
 * 
 * @author zhoupeng
 *
 */
@Entity
@Table
public class BestNews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long newsId;
	@Column(nullable = false, length = 100)
	private String title;
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	@Column(insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDate;
	@Column(nullable = false)
	private String content;
	@Column(nullable = false, length = 4)
	private Integer type;
	@Column(nullable = false, insertable = false)
	private boolean valid;
	@Column(nullable = false)
	private boolean top;// 置顶
	@Column(nullable = false)
	private boolean recommend;// 是否推荐
	@Column(length = 20)
	private String userId;
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	public boolean isRecommend() {
		return recommend;
	}
	public void setRecommend(boolean recommend) {
		this.recommend = recommend;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
