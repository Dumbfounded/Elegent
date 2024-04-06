package com.jkoss.pmsdemo.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jkoss.common.util.CommonUtil;
import com.jkoss.common.util.Constant;
import com.jkoss.pmsdemo.system.entity.Permission;
import com.jkoss.pmsdemo.system.entity.User;
import com.jkoss.pmsdemo.system.service.IPermissionService;
import com.jkoss.pmsdemo.system.service.IUserService;

@Component("AuthRealm")
public class AuthRealm extends AuthorizingRealm{
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IPermissionService iPermissionService;
	// 登录的逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 表单对象
				UsernamePasswordToken uptoken = (UsernamePasswordToken) token;
				// 用户名
				String uname = uptoken.getUsername();
				// 密码
				String pwd = new String(uptoken.getPassword());
				// 根据名字查找
				Wrapper wrapper = Condition.create().eq("name", uname);
				User user = iUserService.selectOne(wrapper);
				if (CommonUtil.isBlank(user)) {
					throw new UnknownAccountException();
				}
				if (!CommonUtil.isEquals(user.getPwd(), pwd)) {
					throw new IncorrectCredentialsException();
				}
				// 查找该用户的所有权限
				List<Permission> permissions=iPermissionService.selectByUid(user.getId());
                if(!CommonUtil.isBlank(permissions)) {
                	List<Permission> menuPermission=new ArrayList();
                	List<String> buttonPermission=new ArrayList();
                	// 过滤权限类型
                	for (Permission permission : permissions) {
						if(CommonUtil.isEquals(permission.getLevel(), 0)) {
							// 等级1
							menuPermission.add(permission);
						}
						else if(CommonUtil.isEquals(permission.getLevel(), 1)) {
							// 等级2
							menuPermission.add(permission);
							buttonPermission.add(permission.getUrl());
						}
						else if(CommonUtil.isEquals(permission.getLevel(), 2)) {
							menuPermission.add(permission);
							buttonPermission.add(permission.getUrl());
						}
					}
                	// 把菜单权限放到session作用域
                	SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_MENU_KEY, menuPermission);
                	// 按钮权限放到session作用域
                	SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_BUTTON_KEY,buttonPermission);
                }
				// 放到session里面
				SecurityUtils.getSubject().getSession().setAttribute(Constant.SESSION_USER_KEY, user);
				// 返回一个登录认证的信息
				return new SimpleAuthenticationInfo(user, user.getPwd(), getName());
	}
	// 认证的逻辑
	// 只要你加上@RequiresPermissions 的注解，每次都会调用认证方法进行认证
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 构造一个认证对象
		SimpleAuthorizationInfo authorizationInfo= new SimpleAuthorizationInfo();
		// 按钮权限放到session作用域
		List<String> buttonPermission=(List<String>) SecurityUtils.getSubject().getSession().getAttribute(Constant.SESSION_BUTTON_KEY);
		// 动态的把该用户的权限放到认证对象里面去
		authorizationInfo.addStringPermissions(buttonPermission);
		return authorizationInfo;
	}

	
}
