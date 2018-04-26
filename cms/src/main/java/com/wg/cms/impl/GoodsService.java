package com.wg.cms.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wg.cms.bean.Goods;
import com.wg.cms.bean.GoodsCondition;
import com.wg.cms.bean.GoodsExample;
import com.wg.cms.bean.GoodsExample.Criteria;
import com.wg.cms.dao.GoodsMapper;
import com.wg.cms.service.IGoodsService;
import com.wg.cms.util.DateTimeUtil;
import com.wg.cms.util.StringUtil;
import com.wg.cms.util.UserUtil;

@Service(value = "goodsService")
public class GoodsService implements IGoodsService {
	// private final Log logger = LogFactory.getLog(getClass());
	@Resource(name = "goodsMapper")
	private GoodsMapper mapper;

	@Override
	public int count(GoodsCondition condition) {
		GoodsExample example = setCommon(condition);
		return mapper.countByExample(example);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Goods record) {
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(Goods record) {
		Integer userId = UserUtil.getUserId();
		String currentTime = DateTimeUtil.getCurrentTime();
		record.setSjgxsj(currentTime);
		record.setSjcjsj(currentTime);
		record.setSjcjr(userId);
		record.setSjgxr(userId);
		return mapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Goods record) {
		Integer userId = UserUtil.getUserId();
		String currentTime = DateTimeUtil.getCurrentTime();
		record.setSjgxr(userId);
		record.setSjgxsj(currentTime);
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Goods record) {
		Integer userId = UserUtil.getUserId();
		String currentTime = DateTimeUtil.getCurrentTime();
		record.setSjgxr(userId);
		record.setSjgxsj(currentTime);
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Goods> select(GoodsCondition condition) {
		GoodsExample example = setCommon(condition);
		return mapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Goods selectByPrimaryKey(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public GoodsExample setCommon(GoodsCondition condition) {
		GoodsExample example = new GoodsExample();
		Criteria criteria = example.createCriteria();
		if (StringUtil.isNotNull(condition.getName())) {
			// criteria.andNameLike("%" + condition.getName() + "%");
		}
		if (StringUtil.isNotNull(condition.getDescription())) {
			criteria.andDescriptionLike("%" + condition.getDescription() + "%");
		}
		if (StringUtil.isNotNull(condition.getType())) {
			criteria.andTypeEqualTo(condition.getType());
		}
		if (StringUtil.isNotNull(condition.getOrder())) {
			String orderBy = condition.getOrder() + " desc";
			example.setOrderByClause(orderBy);
		} else {
			example.setOrderByClause("sjcjsj desc,salenum desc ,getnum desc ");
		}
		if (condition.getStart() != null && condition.getLength() != null) {
			example.setStart(condition.getStart());
			example.setLength(condition.getLength());
		}
		return example;
	}
}
