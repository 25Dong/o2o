package com.mark.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mark.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 1.通过shopId查询该店铺商品类别
	 * @param shopId：店铺的ID
	 * @return 该店铺的商品类别
	 */
	List<ProductCategory> queryProductCategoryList(long shopId);
	
	/**
	 * 2.批量新增商品类别
	 * @param productCategoryList
	 * @return 影响的行数
	 */
	int batchInsertProductCategory(List<ProductCategory> productCategoryList);
	
	/**
	 * 删除指定商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return effectedNum
	 */
	int deleteProductCategory(@Param("productCategoryId") long productCategoryId, @Param("shopId") long shopId);
}
