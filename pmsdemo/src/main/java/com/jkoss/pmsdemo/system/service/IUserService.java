package com.jkoss.pmsdemo.system.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.jkoss.pmsdemo.system.entity.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author chair
 * @since 2018-07-09
 */
public interface IUserService extends IService<User> {
	Page selectVoPage(Page page, Wrapper wrapper);
}
