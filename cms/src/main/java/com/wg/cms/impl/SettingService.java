package com.wg.cms.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wg.cms.bean.Setting;
import com.wg.cms.dao.SettingMapper;
import com.wg.cms.service.ISettingService;

@Service(value = "settingService")
public class SettingService implements ISettingService {
	@Resource(name = "settingMapper")
	private SettingMapper mapper;

	@Override
	public int insert(Setting record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Setting record) {
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKey(Setting record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public Setting selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Setting selectByFlag(String flag) {
		return mapper.selectByFlag(flag);
	}

	@Override
	public int updateByPrimaryKeySelective(Setting record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

}
