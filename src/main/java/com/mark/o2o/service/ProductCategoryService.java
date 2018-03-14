package com.mark.o2o.service;

import java.util.List;

import com.mark.o2o.dto.ProductCategoryExecution;
import com.mark.o2o.entity.ProductCategory;
import com.mark.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 1.通过shopId查询该店铺商品类别
	 * @param shopId：店铺的ID
	 * @return 该店铺的商品类别
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	
	/**
	 * 2.批量新增商品类别
	 * @param productCategory
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException;
	
	/**
	 * 3.将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
			throws ProductCategoryOperationException;	
}
