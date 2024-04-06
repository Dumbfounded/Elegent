package com.jkoss.pmsdemo.system.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jkoss.base.controller.BaseController;
import com.jkoss.common.util.CommonUtil;
import com.jkoss.common.util.Constant;
import com.jkoss.common.util.CryptoUtils;
import com.jkoss.common.vo.DwzPageBean;
import com.jkoss.pmsdemo.system.entity.User;
import com.jkoss.pmsdemo.system.service.IOrganizationService;
import com.jkoss.pmsdemo.system.service.IRoleService;
import com.jkoss.pmsdemo.system.service.IUserService;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author chair
 * @since 2018-07-09
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService iUserService;
	@Autowired
	private IRoleService iRoleService;
	@Autowired
	private IOrganizationService iOrganizationService;

	@RequestMapping("/list")
	//在方法之上加上一个该方法的权限标识
    @RequiresPermissions("/system/user/list")
	public String list(String name, String phone, DwzPageBean dwzPageBean, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		dwzPageBean.getCountResultMap().put("name", name);
		dwzPageBean.getCountResultMap().put("phone", phone);
		Wrapper wrapper = Condition.create().like("u.name", name).like("u.phone", phone);
		// dwzPageBean.toPage() 把前端的分页类转成mybatis plus 的分页类
		Page resultPage = iUserService.selectVoPage(dwzPageBean.toPage(), wrapper);
		// 由于前端要展示的是dwzPageBean ，所以我们要把mybatis 返回的结果继续转为dwzPageBean
		map.put("dwzPageBean", dwzPageBean.toDwzPageBean(resultPage));
		return "system/user/list";
	}
    
	@RequiresPermissions("/system/user/toInsert")
	@RequestMapping("/toInsert")
	public String toInsert(HttpServletRequest request, HttpServletResponse response) {
		return "system/user/edit";
	}
    
	@RequiresPermissions("/system/user/toInsert")
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(User user, HttpServletRequest request, HttpServletResponse response) {
		Wrapper<User> wrapper = Condition.create().eq("name", user.getName()).or().eq("phone", user.getPhone());
		User user2 = iUserService.selectOne(wrapper);
		if (!CommonUtil.isBlank(user2)) {
			return ajaxError("姓名或手机重复");
		}
		// 给用户密码加密
		user.setPwd(CryptoUtils.encodeMD5(user.getPwd()+Constant.YAN_KEY));
		if (iUserService.insert(user)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}

	}

	@RequiresPermissions("/system/user/toUpdate")
	@RequestMapping("/toUpdate")
	public String toUpdate(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("record", iUserService.selectById(id));
		return "system/user/edit";
	}

	@RequiresPermissions("/system/user/toUpdate")
	@RequestMapping("/update")
	@ResponseBody
	public Object update(User user, HttpServletRequest request, HttpServletResponse response) {
		if (iUserService.updateById(user)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequiresPermissions("/system/user/delete")
	@RequestMapping("/delete")
	@ResponseBody
	public Object update(String[] id, HttpServletRequest request, HttpServletResponse response) {
		if (iUserService.deleteBatchIds(Arrays.asList(id))) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}
    
	@RequiresPermissions("/system/user/toUpdateRole")
	@RequestMapping("/toUpdateRole")
	public String toUpdateRole(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// 查找所有角色
		map.put("roles", iRoleService.selectList(null));
		map.put("record", iUserService.selectById(id));
		return "system/user/editRole";
	}

	@RequiresPermissions("/system/user/toUpdateRole")
	@RequestMapping("/updateRole")
	@ResponseBody
	public Object updateRole(String id, String rid, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setId(id);
		user.setRid(rid);
		if (iUserService.updateById(user)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequiresPermissions("/system/user/toUpdateOrganization")
	@RequestMapping("/toUpdateOrganization")
	public String toUpdateOrganization(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// 查找二级部门
		Wrapper wrapper = Condition.create().eq("level", "1");
		map.put("organizations", iOrganizationService.selectList(wrapper));
		map.put("record", iUserService.selectById(id));
		return "system/user/editOrganization";
	}

	@RequiresPermissions("/system/user/toUpdateOrganization")
	@RequestMapping("/updateOrganization")
	@ResponseBody
	public Object updateOrganization(User user, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		if (iUserService.updateById(user)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}
}
