package com.mark.o2o.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.Product;
import com.mark.o2o.entity.ProductCategory;
import com.mark.o2o.entity.Shop;

public class ProductDaoTest extends BaseTest{
	@Autowired
	private ProductDao productDao;


	//1.插入商品
	@Test
	public void testInsertProduct(){
		Shop shop1 = new Shop();
		shop1.setShopId(1L);
		ProductCategory productCategory1 = new ProductCategory();
		productCategory1.setProductCategoryId(1L);

		// 初始化三个商品实例并添加进shopId为1的店铺里，
		// 同时商品类别Id也为1
		Product product1 = new Product();
		product1.setProductName("测试1");
		product1.setProductDesc("测试desc1");
		product1.setImgAddr("test1");
		product1.setNormalPrice("1");
		product1.setPromotionPrice("2");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setLastEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(productCategory1);

		Product product2 = new Product();
		product2.setProductName("测试2");
		product2.setProductDesc("测试Desc2");
		product2.setImgAddr("test2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setLastEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(productCategory1);
		Product product3 = new Product();
		product3.setProductName("test3");
		product3.setProductDesc("测试Desc3");
		product3.setImgAddr("test3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setLastEditTime(new Date());
		product3.setShop(shop1);
		product3.setProductCategory(productCategory1);

		// 判断添加是否成功
		int effectedNum = productDao.insertProduct(product1);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product2);
		assertEquals(1, effectedNum);
		effectedNum = productDao.insertProduct(product3);
		assertEquals(1, effectedNum);
	}
}
