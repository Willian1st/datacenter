package com.wg.cms.service;

import com.wg.cms.bean.Setting;

public interface ISettingService {

	int insert(Setting record);

	int insertSelective(Setting record);

	int updateByPrimaryKey(Setting record);

	int updateByPrimaryKeySelective(Setting record);

	Setting selectByPrimaryKey(Integer id);

	Setting selectByFlag(String flag);

}
