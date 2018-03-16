package com.mark.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mark.o2o.BaseTest;
import com.mark.o2o.dto.HeadLineExcution;
import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.entity.HeadLine;
import com.mark.o2o.enums.HeadLineStateEnum;

public class HeadLineServiceTest extends BaseTest{
	@Autowired
	private HeadLineService headLineService;
	
	
	@Test
	public void testAddHeadLine() throws FileNotFoundException{
		for(int i=1;i<5;i++){
			HeadLine headLine = new HeadLine();
			headLine.setLineName("testNameSeriver");
			headLine.setLineLink("testLinkSeriver");
			headLine.setLineImg("testImgSeriver");
			headLine.setPriority(1);
			File headLineImg  = new File("E:\\workspace_MARK\\image\\"+i+".jpg");
			InputStream is = new FileInputStream(headLineImg);
			ImageHolder thumbnail = new ImageHolder(headLineImg.getName(), is);
			HeadLineExcution se = headLineService.addHeadLine(headLine, thumbnail);
			assertEquals(HeadLineStateEnum.SUCCESS.getState(), se.getState());
		}
	}
}
