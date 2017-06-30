package jp.co.POS.alh.mapper;

import org.apache.ibatis.annotations.Param;

import jp.co.POS.alh.entity.Goods;

public interface GoodsMapper {
	Goods getGoods(@Param("code") String code);
}
