package com.mark.o2o.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.dto.ProductCategoryExecution;
import com.mark.o2o.entity.ProductCategory;
import com.mark.o2o.exceptions.ProductCategoryOperationException;

public class ProductCategoryServiceTest extends BaseTest{
	@Autowired
	ProductCategoryService productCategoryService;
	
	@Test//测试通过
	@Ignore
	public void testQueryProductCategoryList(){
		Long shopId = 40L;
		List<ProductCategory> ProductCategorys = productCategoryService.getProductCategoryList(shopId);
		System.out.println("商品id为："+shopId+"下的商品类别有"+ProductCategorys.size()+"种");
	}
	
	@Test//
	public void testBatchAddProductCategory() throws ProductCategoryOperationException{

		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("商品类别1");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(50L);
		
		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("商品类别2");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(50L);
		
		List<ProductCategory> productCategories = new ArrayList<ProductCategory>();
		productCategories.add(productCategory);
		productCategories.add(productCategory2);
		
		ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(productCategories);
		System.out.println(pe.getStateInfo());

		
	
	}
}
