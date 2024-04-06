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
import com.jkoss.pmsdemo.system.entity.Organization;
import com.jkoss.pmsdemo.system.entity.Permission;
import com.jkoss.pmsdemo.system.service.IOrganizationService;

 /**
 * 组织机构 前端控制器
 * 
 * @Author chair
 * @Version 1.0, 2018-11-08
 * @See
 * @Since com.jkoss.pmsdemo.system.controller
 * @Description: TODO
 */
@Controller
@RequestMapping("/system/organization")
public class OrganizationController extends BaseController {


	@Autowired
	private IOrganizationService iOrganizationService;
	
	@RequestMapping("/list")
	public String list(DwzPageBean dwzPageBean, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		Wrapper wrapper = Condition.create().orderBy("level", true).orderBy("sort", true);
		List<Organization> organizations = iOrganizationService.selectList(wrapper);
		// 把查询回来的对象转换成ztree适配的对象
		if (!CommonUtil.isBlank(organizations)) {
			List<ZtreeBean> ztreeBeans = new ArrayList();
			for (Organization organization : organizations) {
				ZtreeBean ztreeBean = new ZtreeBean();
				ztreeBean.setId(organization.getId());
				ztreeBean.setPId(organization.getPid());
				ztreeBean.setName(organization.getName());
				ztreeBean.setOpen(true);
				ztreeBeans.add(ztreeBean);
			}
			map.put("ztreeBeans", JSON.toJSONString(ztreeBeans));
		}else {
			map.put("ztreeBeans","[]");
		}
		return "system/organization/list";
	}

	@RequestMapping("/toInsert")
	public String toInsert(String pid,Integer level, ModelMap map,HttpServletRequest request, HttpServletResponse response) {
		map.put("pid", pid);
		map.put("level", level);
		return "system/organization/edit";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert( Organization organization, HttpServletRequest request, HttpServletResponse response) {
		if (iOrganizationService.insert(organization)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}

	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("record", iOrganizationService.selectById(id));
		return "system/organization/edit";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update( Organization organization, HttpServletRequest request, HttpServletResponse response) {
		if (iOrganizationService.updateById(organization)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(String[] id, HttpServletRequest request, HttpServletResponse response) {
		if (iOrganizationService.deleteBatchIds(Arrays.asList(id))) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}
	

}

