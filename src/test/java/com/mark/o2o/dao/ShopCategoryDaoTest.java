package com.mark.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.ShopCategory;

public class ShopCategoryDaoTest extends BaseTest{
	@Autowired  ShopCategoryDao shopCategoryDao;
	
	//测试查询语句
	@Test
	public void testQueryShopCategory(){
	
		ShopCategory shopCategoryCOndition = new ShopCategory();
		shopCategoryCOndition.setShopCategoryId(1L);
		List<ShopCategory> shopCategories=shopCategoryDao.queryShopCategory(shopCategoryCOndition);
		for(ShopCategory shopCategory:shopCategories){
			System.out.println(shopCategory.getShopCategoryDesc()+shopCategory.getShopCategoryName());
		}
	}

}
