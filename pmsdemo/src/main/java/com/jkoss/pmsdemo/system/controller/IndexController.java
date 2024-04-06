package com.jkoss.pmsdemo.system.controller;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jkoss.base.controller.BaseController;
import com.jkoss.common.util.Constant;
import com.jkoss.pmsdemo.system.service.IUserService;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

	@Autowired
	private IUserService iUserService;

	@RequestMapping("/")
	public String toIndex() {
		return "index";
	}
	@RequestMapping("/login")
	public String login(String name, String pwd, ModelMap map, HttpSession session) throws Exception {
		//构造登录参数
				UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
				try {
					//交给Realm类处理
					SecurityUtils.getSubject().login(token);
				} catch (UnknownAccountException uae) {
					map.put("msg", "未知用户");
					return "login";
				} catch (IncorrectCredentialsException ice) {
					map.put("msg", "密码错误");
					return "login";
				} catch (AuthenticationException ae) {
					// unexpected condition? error?
					map.put("msg", "服务器繁忙");
					return "login";
				}
				return "redirect:/";
	}

	/*@RequestMapping("/login")
	public String login(String name, String pwd, ModelMap map, HttpSession session) throws Exception {
		// 根据名字查找
		Wrapper wrapper = Condition.create().eq("name", name);
		User user = iUserService.selectOne(wrapper);
		if (!CommonUtil.isBlank(user)) {
			if (CommonUtil.isEquals(pwd, user.getPwd())) {

				// 登录时间
				user.setLogintime(CommonUtil.date6());
				// 获取ip
				user.setLoginip(getAddr());

				if (iUserService.updateById(user)) {
					// 匹配成功
					session.setAttribute(Constant.SESSION_USER_KEY, user);
					return "redirect:/";
				}
				map.put("msg", "登录失败");
			} else {
				// 密码不对
				map.put("msg", "密码错误");
			}
		} else {
			// 没有该帐号
			map.put("msg", "用户不存在");
		}
		return "login";
	}*/

	@RequestMapping("/toLogout")
	public String toLogout() {
		return "logoutConfirm";
	}

	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Constant.SESSION_USER_KEY);
		return "redirect:/toLogin";
	}
}
