package com.wg.cms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wg.cms.bean.User;
import com.wg.cms.bean.UserCondition;
import com.wg.cms.bean.UserExample;
import com.wg.cms.bean.UserExample.Criteria;
import com.wg.cms.dao.UserMapper;
import com.wg.cms.service.IUserService;
import com.wg.cms.util.StringUtil;

@Service(value = "userService")
public class UserService implements IUserService {
	@Resource(name = "userMapper")
	private UserMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(User record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(User record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public UserExample setCommon(UserCondition condition) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		if (StringUtil.isNotNull(condition.getUsername())) {
			criteria.andUsernameLike("%" + condition.getUsername() + "%");
		}
		if (StringUtil.isNotNull(condition.getOrder())) {
			String orderBy = condition.getOrder() + " desc";
			example.setOrderByClause(orderBy);
		}
		return example;
	}

	@Override
	public User selectByUserName(String username) {
		return mapper.selectByUserName(username);
	}
}
