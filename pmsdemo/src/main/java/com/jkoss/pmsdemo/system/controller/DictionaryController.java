package com.jkoss.pmsdemo.system.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jkoss.common.vo.DwzPageBean;
import com.jkoss.pmsdemo.system.entity.Dictionary;
import com.jkoss.pmsdemo.system.service.IDictionaryService;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author chair
 * @since 2018-07-09
 */
@Controller
@RequestMapping("/system/dictionary")
public class DictionaryController extends BaseController {

	@Autowired
	private IDictionaryService iDictionaryService;

	@RequestMapping("/list")
	public String list(String name, String phone, DwzPageBean dwzPageBean, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		// dwzPageBean.toPage() 把前端的分页类转成mybatis plus 的分页类
		Page resultPage = iDictionaryService.selectPage(dwzPageBean.toPage(), null);
		// 由于前端要展示的是dwzPageBean ，所以我们要把mybatis 返回的结果继续转为dwzPageBean
		map.put("dwzPageBean", dwzPageBean.toDwzPageBean(resultPage));
		return "system/dictionary/list";
	}

	@RequestMapping("/toInsert")
	public String toInsert(HttpServletRequest request, HttpServletResponse response) {
		return "system/dictionary/edit";
	}

	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(Dictionary dictionary, HttpServletRequest request, HttpServletResponse response) {

		// 防止key重复
		Wrapper wrapper = Condition.create().eq("dkey", dictionary.getDkey());
		Dictionary tmp = iDictionaryService.selectOne(wrapper);
		if (!CommonUtil.isBlank(tmp)) {
			// key重复
			return ajaxError("key重复");
		}

		if (iDictionaryService.insert(dictionary)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequestMapping("/toUpdate")
	public String toUpdate(String id, ModelMap map, HttpServletRequest request, HttpServletResponse response) {
		map.put("record", iDictionaryService.selectById(id));
		return "system/dictionary/edit";
	}

	@RequestMapping("/update")
	@ResponseBody
	public Object update(Dictionary dictionary, HttpServletRequest request, HttpServletResponse response) {
		if (iDictionaryService.updateById(dictionary)) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Object update(String[] id, HttpServletRequest request, HttpServletResponse response) {
		if (iDictionaryService.deleteBatchIds(Arrays.asList(id))) {
			return ajaxSuccess();
		} else {
			return ajaxError();
		}
	}

}
