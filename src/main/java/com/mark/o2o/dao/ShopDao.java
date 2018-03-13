package com.mark.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mark.o2o.entity.Shop;

public interface ShopDao {
	//1.新增店铺
	int insertShop(Shop shop);
	
	//2.更新店铺信息
	int updateShop(Shop shop);
	
	//3.通过shop id查询店铺
	Shop queryByShopId(long shopId);
	
	/**
	 * 4.分页查询店铺，可输入的条件有：店铺名(模糊),店铺状态，店铺类别，区域Id,owner
	 * 
	 * @param shopCondition
	 * @param rowIndex
	 *            从第几行开始取数据
	 * @param pageSize
	 *            返回的条数
	 * @return
	 * 当参数个数只有一个的时候可以不写Param注解
	 */
	List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	
	/**
	 * 返回queryShopList总数
	 * 
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
