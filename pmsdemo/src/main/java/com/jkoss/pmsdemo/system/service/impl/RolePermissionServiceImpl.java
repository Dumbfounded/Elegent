package com.jkoss.pmsdemo.system.service.impl;

import com.jkoss.common.util.CommonUtil;
import com.jkoss.pmsdemo.system.entity.RolePermission;
import com.jkoss.pmsdemo.system.mapper.RolePermissionMapper;
import com.jkoss.pmsdemo.system.service.IRolePermissionService;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


/**
 * 角色权限中间表 服务实现类
 * 
 * @Author chair
 * @Version 1.0, 2018-11-10
 * @See
 * @Since com.jkoss.pmsdemo.system.service.impl
 * @Description: TODO
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IRolePermissionService {

	@Override
	public List<String> selectPidByRid(String rid) {

		return baseMapper.selectPidByRid(rid);
	}

	@Override
	public boolean batchUpdatePermission(String rid, String[] pid) {
		// 把以前权限全部删除
				Wrapper wrapper = Condition.create().eq("rid", rid);
				this.baseMapper.delete(wrapper);
				// 新增新所有权限
				if (!CommonUtil.isBlank(pid)) {
					List<RolePermission> rolePermissions = new ArrayList();
					for (String tmpPid : pid) {
						RolePermission rolePermission = new RolePermission();
						rolePermission.setRid(rid);
						rolePermission.setPid(tmpPid);
						rolePermissions.add(rolePermission);
					}
					this.insertBatch(rolePermissions);
				}
				return true;
	}

}
