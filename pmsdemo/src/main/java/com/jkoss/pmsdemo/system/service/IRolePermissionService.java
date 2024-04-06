package com.jkoss.pmsdemo.system.service;

import com.jkoss.pmsdemo.system.entity.RolePermission;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * 角色权限中间表 服务类
 * 
 * @Author chair
 * @Version 1.0, 2018-11-10
 * @See
 * @Since com.jkoss.pmsdemo.system.service
 * @Description: TODO
 */
public interface IRolePermissionService extends IService<RolePermission> {
	List<String> selectPidByRid(String rid);

	boolean batchUpdatePermission(String id, String[] pid);
}
