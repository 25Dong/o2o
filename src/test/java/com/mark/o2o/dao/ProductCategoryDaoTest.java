package com.mark.o2o.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	@Autowired
	private ProductCategoryDao productCategoryDao;
	//1.通过shopId查询该店铺商品类别
	@Test//success
	@Ignore
	public void testQueryProductCategoryList() throws Exception{
		long shopId = 40L;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("该店铺的自定义的类别数目共有"+productCategoryList.size());
	}
	
	//2.批量新增商品类别
	@Test
	@Ignore
	public void testBatchInsertProductCategory(){
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
		
		int effectNum = productCategoryDao.batchInsertProductCategory(productCategories);
		assertEquals(2,effectNum);
	}
	
	//3.删除
	@Test
	public void testDeleteProductCategory(){
		long shopId = 50L;
		int productCategoryId = 12;
		int effectNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
		assertEquals(1,effectNum);
	}
}
