package com.mark.o2o.dao;

import java.util.List;

import com.mark.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 通过shopId查询该店铺商品类别
	 * @param shopId：店铺的ID
	 * @return 该店铺的商品类别
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
}
