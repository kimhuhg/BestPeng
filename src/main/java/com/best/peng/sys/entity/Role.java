package com.best.peng.sys.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * 角色类
 * @author zhoupeng
 *
 */
@Entity
@Table
public class Role implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable=false,length=20)
	private String name;
	
	@Column(length=100)
	private String description;
	
	//角色权限
	@ManyToMany
	@JoinColumn
//	@JoinTable(name="role_permission",
//    joinColumns={@JoinColumn(name="role_id")},
//    inverseJoinColumns={@JoinColumn(name="permission_id")})
	private List<Menu> menus;//多对多,一个角色对应多个权限
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
}
