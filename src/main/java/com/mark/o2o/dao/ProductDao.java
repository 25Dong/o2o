package com.mark.o2o.dao;

import com.mark.o2o.entity.Product;

public interface ProductDao {

	/**
	 * 1.插入商品
	 * @param product
	 * @return 影响的行数 期望值返回的是1
	 */
	int insertProduct(Product product);
}
