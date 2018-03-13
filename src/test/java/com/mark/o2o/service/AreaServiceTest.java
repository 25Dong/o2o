package com.mark.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areService;
	@Test
	public void testGetAreaList(){
		List<Area> arealist = areService.getAreaList();
		System.out.println(arealist);
	}
}
