package com.mark.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.HeadLine;

public class HeadLineDaoTest extends BaseTest{
	@Autowired
	private HeadLineDao headLineDao;
	
	@Test
	@Ignore
	public void testInsertheadLine(){
		HeadLine headLine = new HeadLine();
		headLine.setLineName("testName");
		headLine.setLineLink("testLink");
		headLine.setLineImg("testImg");
		headLine.setPriority(1);
		headLine.setEnableStatus(1);
		headLine.setCreateTime(new Date());
		headLine.setLastEditTime(new Date());
		int effectNum = headLineDao.insertheadLine(headLine);
		assertEquals(1, effectNum);
	}
	@Test
	public void testUpdateHeadLine(){
		HeadLine headLine = new HeadLine();
		headLine.setLineId(1L);
		headLine.setLineName("gengxin");
		headLine.setLineImg("gengxinaddr");
		int effectNum = headLineDao.updateHeadLine(headLine);
		assertEquals(1, effectNum);
	}
	
	@Test
	@Ignore
	public void testQueryHeadLine(){
		List<HeadLine> heaLineList = new ArrayList<HeadLine>();
		heaLineList = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(2,heaLineList.size());
	}

}
