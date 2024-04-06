package com.jkoss.pmsdemo.system.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jkoss.base.controller.BaseController;
import com.jkoss.common.util.CommonUtil;
import com.jkoss.common.vo.DwzPageBean;
import com.jkoss.common.vo.ZtreeBean;
import com.jkoss.pmsdemo.system.entity.Permission;
import com.jkoss.pmsdemo.system.entity.Role;
import com.jkoss.pmsdemo.system.entity.RolePermission;
import com.jkoss.pmsdemo.system.service.IPermissionService;
import com.jkoss.pmsdemo.system.service.IRolePermissionService;
import com.jkoss.pmsdemo.system.service.IRoleService;

 /**
 * 角色 前端控制器
 * 
 * @Author chair
 * @Version 1.0, 2018-11-06
 * @See
 * @Since com.jkoss.pmsdemo.system.controller
 * @Description: TODO
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {


	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private IPermissionService iPermissionService;
	@Autowired
	private IRolePermissionService iRolePermissionService;

	
	@RequestMapping("/list")
	public String list(DwzPageBean dwzPageBean, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		Page resultPage = iRoleService.selectPage(dwzPageBean.toPage(), null);
		map.put("dwzPageBean", dwzPageBean.toDwzPageBean(resultPage));
		return "system/role/list";
	}

	@RequestMapping("/toInsert")
	public String toInsert(HttpServletRequest request, HttpServletResponse response) {
		return "system/role/edit";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert( Role role, HttpServletRequest request, HttpServletResponse response) {
		if (iRoleService.insert(role)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}

	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("record", iRoleService.selectById(id));
		return "system/role/edit";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update( Role role, HttpServletRequest request, HttpServletResponse response) {
		if (iRoleService.updateById(role)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String[] id, HttpServletRequest request, HttpServletResponse response) {
		if (iRoleService.deleteBatchIds(Arrays.asList(id))) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}
	@RequestMapping("/toUpdatePermission")
	public String toUpdatePermission(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {

		// 查询该角色的所有权限
		List<String> pids = iRolePermissionService.selectPidByRid(id);

		// 由于是树形结构不需要分页
		Wrapper wrapper = Condition.create().orderBy("level", true).orderBy("sort", true);
		List<Permission> permissions = iPermissionService.selectList(wrapper);
		// 把查询回来的对象转换成ztree适配的对象
		if (!CommonUtil.isBlank(permissions)) {
			List<ZtreeBean> ztreeBeans = new ArrayList();
			for (Permission permission : permissions) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(permission.getId());
				ztreeBean.setPId(permission.getPid());
				ztreeBean.setName(permission.getName());
				ztreeBean.setOpen(true);

				if (!CommonUtil.isBlank(pids)) {
					ztreeBean.setChecked(pids.contains(permission.getId()));
				}

				ztreeBeans.add(ztreeBean);
			}
			map.put("ztreeBeans", JSON.toJSONString(ztreeBeans));
		} else {
			map.put("ztreeBeans", "[]");
		}

		map.put("id", id);

		return "system/role/editPermission";
	}
	@RequestMapping("/updatePermission")
	@ResponseBody
	public Object updatePermission(String id, String[] pid, HttpServletRequest request, HttpServletResponse response) {
		if (iRolePermissionService.batchUpdatePermission(id, pid)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

}

