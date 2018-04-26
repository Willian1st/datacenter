package com.wg.cms.dao;

import com.wg.cms.bean.User;
import com.wg.cms.bean.UserExample;
import java.util.List;

public interface UserMapper {
	int countByExample(UserExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(User record);

	int insertSelective(User record);

	List<User> selectByExample(UserExample example);

	User selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	User selectByUserName(String username);
}