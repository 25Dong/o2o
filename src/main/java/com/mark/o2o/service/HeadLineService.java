package com.mark.o2o.service;

import java.io.IOException;
import java.util.List;

import com.mark.o2o.dto.HeadLineExcution;
import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.entity.HeadLine;

public interface HeadLineService {
	
	/**
	 * 1.注册店铺信息，包括图片处理
	 * @param headLine
	 * @param thumbnail
	 * @return
	 */
	HeadLineExcution addHeadLine(HeadLine headLine,ImageHolder thumbnail);
	
	/**
	 * 2..根据传入的条件返回指定的头条列表
	 * @param headLineCondition
	 * @return
	 * @throws IOException
	 */
	List<HeadLine> getHeadLineList(HeadLine headLineCondition);
}
