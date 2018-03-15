package com.mark.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mark.o2o.entity.Product;

public interface ProductDao {

	/**
	 * 1.插入商品
	 * @param product
	 * @return 影响的行数 期望值返回的是1
	 */
	int insertProduct(Product product);
	
	/**
	 * 2.通过productId查询唯一的商品信息
	 * 
	 * @param productId
	 * @return
	 */
	Product queryProductById(long productId);
	

	/**
	 * 3.更新商品信息
	 * 
	 * @param product
	 * @return
	 */
	int updateProduct(Product product);
	

	/**
	 * 4.1查询对应的商品总数
	 * 
	 * @param productCondition
	 * @return
	 */
	int queryProductCount(@Param("productCondition") Product productCondition);
	
	/**
	 * 4.2 查询商品列表并分页，可输入的条件有： 商品名（模糊），商品状态，店铺Id,商品类别
	 * @param productCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Product> queryProductList(@Param("productCondition")Product productCondition,
			@Param("rowIndex") int rowIndex,
			@Param("pageSize") int pageSize);
	

	/**
	 * 5.删除商品类别之前，将商品类别ID置为空
	 * 
	 * @param productCategoryId
	 * @return
	 */
	int updateProductCategoryToNull(long productCategoryId);


}

