package com.jkoss.pmsdemo.system.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import com.ossjk.pms.base.controller.BaseController;

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
		Page resultPage = iPermissionService.selectPage(dwzPageBean.toPage(), null);
		map.put("dwzPageBean", dwzPageBean.toDwzPageBean(resultPage));
		return "system/permission/list";
	}

	@RequestMapping("/toInsert")
	public String toInsert(HttpServletRequest request, HttpServletResponse response) {
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

