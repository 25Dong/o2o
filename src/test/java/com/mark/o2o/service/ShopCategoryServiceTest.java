package com.mark.o2o.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;

public class ShopCategoryServiceTest  extends BaseTest{
	@Autowired
	private ShopCategoryService shopCategoryService;
	
	@Test
	public void testGetShopCategoryList(){
		shopCategoryService.getShopCategoryList(null);
	}
}
