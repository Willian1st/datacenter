package com.wg.cms.service;

import com.wg.cms.bean.User;

public interface IUserService {

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByPrimaryKey(Integer id);

	User selectByUserName(String username);

}
