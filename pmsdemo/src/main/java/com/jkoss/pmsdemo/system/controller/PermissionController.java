package com.jkoss.pmsdemo.system.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jkoss.common.util.CommonUtil;
import com.jkoss.common.vo.DwzPageBean;
import com.jkoss.common.vo.ZtreeBean;
import com.jkoss.pmsdemo.system.entity.Permission;
import com.jkoss.pmsdemo.system.service.IPermissionService;
import com.jkoss.base.controller.BaseController;

 /**
 * 权限 前端控制器
 * 
 * @Author chair
 * @Version 1.0, 2018-11-06
 * @See
 * @Since com.jkoss.pmsdemo.system.controller
 * @Description: TODO
 */
@Controller
@RequestMapping("/system/permission")
public class PermissionController extends BaseController {


	@Autowired
	private IPermissionService iPermissionService;
	
	@RequestMapping("/list")
	public String list(DwzPageBean dwzPageBean, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
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
						ztreeBeans.add(ztreeBean);
					}
					map.put("ztreeBeans", JSON.toJSONString(ztreeBeans));
				}else {
					map.put("ztreeBeans","[]");
				}
		return "system/permission/list";
	}

	@RequestMapping("/toInsert")
	public String toInsert(String pid, Integer level, ModelMap map,HttpServletRequest request, HttpServletResponse response) {
		map.put("pid", pid);
		map.put("level", level);
		return "system/permission/edit";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert( Permission permission, HttpServletRequest request, HttpServletResponse response) {
		if (iPermissionService.insert(permission)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}

	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("record", iPermissionService.selectById(id));
		return "system/permission/edit";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update( Permission permission, HttpServletRequest request, HttpServletResponse response) {
		if (iPermissionService.updateById(permission)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String[] id, HttpServletRequest request, HttpServletResponse response) {
		if (iPermissionService.deleteBatchIds(Arrays.asList(id))) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}
	

}

