package com.wg.cms.dao;

import com.wg.cms.bean.Goods;
import com.wg.cms.bean.GoodsExample;
import java.util.List;

public interface GoodsMapper {
	int countByExample(GoodsExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Goods record);

	int insertSelective(Goods record);

	List<Goods> selectByExampleWithBLOBs(GoodsExample example);

	List<Goods> selectByExample(GoodsExample example);

	Goods selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Goods record);

	int updateByPrimaryKeyWithBLOBs(Goods record);

	int updateByPrimaryKey(Goods record);
}