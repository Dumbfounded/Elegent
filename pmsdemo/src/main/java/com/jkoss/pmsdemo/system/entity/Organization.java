package com.jkoss.pmsdemo.system.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.jkoss.base.entity.BaseEntity;


/**
 * 组织机构
 * 
 * @Author chair
 * @Version 1.0, 2018-11-08
 * @See
 * @Since com.jkoss.pmsdemo.system.entity
 * @Description: TODO
 */
public class Organization extends BaseEntity<Organization> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 父级id
     */
    @TableField("pid")
    private String pid;
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
    /**
     * 排序
     */
    @TableField("sort")
    private BigDecimal sort;
    /**
     * 等级
     */
    @TableField("level")
    private BigDecimal level;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Organization{" +
        ", id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", remarks=" + remarks +
        ", sort=" + sort +
        ", level=" + level +
        "}";
    }
}
