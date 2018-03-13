package com.mark.o2o.service;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.dto.ShopExecution;
import com.mark.o2o.entity.Area;
import com.mark.o2o.entity.PersonInfo;
import com.mark.o2o.entity.Shop;
import com.mark.o2o.entity.ShopCategory;
import com.mark.o2o.enums.ShopStateEnum;
import com.mark.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	@Ignore
	public void testAddShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(9L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("E:\\workspace_MARK\\image\\xiaohuangren.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder );
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
	
	@Test
	@Ignore
	public void TestModifyShop() throws FileNotFoundException{
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(9L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setShopId(40L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3更新了");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		//更新图片路径
		//File shopImg = new File("E:\\workspace_MARK\\image\\xiaohuangren.jpg");
		File shopImg = new File("E:\\workspace_MARK\\image\\dabai.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		
		ShopExecution se = shopService.modifyShop(shop, imageHolder);
	}
	
	//shopCondition分页返回相应店铺列表
	@Test
	public void TestGetShopList(){
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		childCategory.setShopCategoryId(1L);
		shopCondition.setShopCategory(childCategory);
		ShopExecution se = shopService.getShopList(shopCondition, 2, 6);
		int count = se.getCount();
		System.out.println("店铺列表的大小：" + se.getShopList().size());
		System.out.println("店铺总数：" + count);	
	}
	
	
}
