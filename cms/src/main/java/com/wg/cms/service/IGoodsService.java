package com.wg.cms.service;

import java.util.List;

import com.wg.cms.bean.Goods;
import com.wg.cms.bean.GoodsCondition;

public interface IGoodsService {
	int count(GoodsCondition condition);

	int deleteByPrimaryKey(Integer id);

	int insert(Goods record);

	int insertSelective(Goods record);

	int updateByPrimaryKeySelective(Goods record);

	int updateByPrimaryKey(Goods record);

	List<Goods> select(GoodsCondition condition);

	Goods selectByPrimaryKey(Integer id);

}
