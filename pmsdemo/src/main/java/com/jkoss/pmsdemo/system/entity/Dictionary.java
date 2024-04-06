package com.jkoss.pmsdemo.system.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.jkoss.base.entity.BaseEntity;

/**
 * 数据字典
 * 
 * @Author chair
 * @Version 1.0, 2018-11-04
 * @See
 * @Since com.jkoss.pmsdemo.system.entity
 * @Description: TODO
 */
public class Dictionary extends BaseEntity<Dictionary> {

	private static final long serialVersionUID = 1L;

	@TableId("id")
	private String id;
	/**
	 * 键
	 */
	@TableField("dkey")
	private String dkey;
	/**
	 * 值
	 */
	@TableField("dvalue")
	private String dvalue;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDkey() {
		return dkey;
	}

	public void setDkey(String dkey) {
		this.dkey = dkey;
	}

	public String getDvalue() {
		return dvalue;
	}

	public void setDvalue(String dvalue) {
		this.dvalue = dvalue;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Dictionary{" + ", id=" + id + ", dkey=" + dkey + ", dvalue=" + dvalue + "}";
	}
}
