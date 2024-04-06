package com.jkoss.pmsdemo.system.mapper;

import com.jkoss.pmsdemo.system.entity.RolePermission;

import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * 角色权限中间表 Mapper 接口
 * 
 * @Author chair
 * @Version 1.0, 2018-11-10
 * @See
 * @Since com.jkoss.pmsdemo.system.mapper
 * @Description: TODO
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	List<String> selectPidByRid(String rid);

}
