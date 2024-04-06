package com.jkoss.pmsdemo.system.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jkoss.pmsdemo.system.entity.User;
import com.jkoss.pmsdemo.system.mapper.UserMapper;
import com.jkoss.pmsdemo.system.service.IUserService;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author chair
 * @since 2018-07-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

	@Override
	public Page selectVoPage(Page page, Wrapper wrapper) {
		// 转换wrapper的sql条件,并且会把转换好的sql赋值给wrapper对象的sqlSegment属性
		SqlHelper.fillWrapper(page, wrapper);
		page.setRecords(baseMapper.selectVoPage(page, wrapper));
		return page;
	}

}
