package com.mark.o2o.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.ProductImg;

public class ProductImgDaoTest extends BaseTest{
	@Autowired
	private ProductImgDao productImgDao;
	
	//1.批量添加商品详情图片
	@Test
	public void testABatchInsertProductImg(){
		// productId为1的商品里添加两个详情图片记录
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("测试图片1的地址");
		productImg1.setImgDesc("测试图片1的描述");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);
		
		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("测试图片2的地址");
		productImg2.setImgDesc("测试图片2的描述");
		productImg2.setPriority(2);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);
		
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2,effectNum);
	}

}
