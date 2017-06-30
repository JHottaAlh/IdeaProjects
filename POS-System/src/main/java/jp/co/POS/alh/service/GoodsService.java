package jp.co.POS.alh.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.POS.alh.dto.GoodsDto;
import jp.co.POS.alh.entity.Goods;
import jp.co.POS.alh.mapper.GoodsMapper;

@Service
public class GoodsService{
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Transactional
	public GoodsDto getGoods(String code){
		GoodsDto dto = new GoodsDto();
		Goods entity = new Goods();
		entity = goodsMapper.getGoods(code);
		BeanUtils.copyProperties(entity, dto);
		
		return dto;
	}
}
