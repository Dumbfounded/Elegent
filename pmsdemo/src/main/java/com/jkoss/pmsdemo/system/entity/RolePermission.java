package com.jkoss.pmsdemo.system.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.jkoss.base.entity.BaseEntity;


/**
 * 角色权限中间表
 * 
 * @Author chair
 * @Version 1.0, 2018-11-10
 * @See
 * @Since com.jkoss.pmsdemo.system.entity
 * @Description: TODO
 */
@TableName("role_permission")
public class RolePermission extends BaseEntity<RolePermission> {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private String id;
    /**
     * 角色id
     */
    @TableField("rid")
    private String rid;
    /**
     * 权限id
     */
    @TableField("pid")
    private String pid;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RolePermission{" +
        ", id=" + id +
        ", rid=" + rid +
        ", pid=" + pid +
        "}";
    }
}
