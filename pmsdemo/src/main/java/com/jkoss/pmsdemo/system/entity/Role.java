package com.jkoss.pmsdemo.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.jkoss.base.entity.BaseEntity;

/**
 * 角色
 * 
 * @Author chair
 * @Version 1.0, 2018-11-06
 * @See
 * @Since com.jkoss.pmsdemo.system.entity
 * @Description: TODO
 */
public class Role extends BaseEntity<Role> {

	private static final long serialVersionUID = 1L;

	/**
	 * 名字
	 */
	@TableField("name")
	private String name;
	/**
	 * 备注
	 */
	@TableField("remarks")
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Role{" + ", id=" + id + ", name=" + name + ", remarks=" + remarks + "}";
	}
}
