package com.wg.cms.dao;

import com.wg.cms.bean.Setting;
import com.wg.cms.bean.SettingExample;
import java.util.List;

public interface SettingMapper {
	int countByExample(SettingExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Setting record);

	int insertSelective(Setting record);

	List<Setting> selectByExample(SettingExample example);

	Setting selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Setting record);

	int updateByPrimaryKey(Setting record);

	Setting selectByFlag(String flag);
}