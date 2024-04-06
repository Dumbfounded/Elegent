package com.jkoss.pmsdemo.system.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.jkoss.base.entity.BaseEntity;


/**
 * 权限
 * 
 * @Author chair
 * @Version 1.0, 2018-11-06
 * @See
 * @Since com.jkoss.pmsdemo.system.entity
 * @Description: TODO
 */
public class Permission extends BaseEntity<Permission> {

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
     * url
     */
    @TableField("url")
    private String url;
    /**
     * 序号
     */
    @TableField("sort")
    private BigDecimal sort;
    /**
     * 备注
     */
    @TableField("remarks")
    private String remarks;
    /**
     * 等级
     */
    @TableField("level")
    private BigDecimal level;
    /**
     * 图标
     */
    @TableField("icon")
    private String icon;
    /**
     * 类型 1-显示菜单、2-功能菜单、3-功能按钮
     */
    @TableField("type")
    private BigDecimal type;


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BigDecimal getSort() {
        return sort;
    }

    public void setSort(BigDecimal sort) {
        this.sort = sort;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getLevel() {
        return level;
    }

    public void setLevel(BigDecimal level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Permission{" +
        ", id=" + id +
        ", pid=" + pid +
        ", name=" + name +
        ", url=" + url +
        ", sort=" + sort +
        ", remarks=" + remarks +
        ", level=" + level +
        ", icon=" + icon +
        ", type=" + type +
        "}";
    }
}
