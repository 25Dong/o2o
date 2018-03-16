package com.mark.o2o.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mark.o2o.dao.HeadLineDao;
import com.mark.o2o.dto.HeadLineExcution;
import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.entity.HeadLine;
import com.mark.o2o.enums.HeadLineStateEnum;
import com.mark.o2o.exceptions.HeadLineOperationException;
import com.mark.o2o.service.HeadLineService;
import com.mark.o2o.util.ImageUtil;
import com.mark.o2o.util.PathUtil;

@Service
public class HeadLineServiceImpl implements HeadLineService{
	@Autowired
	private HeadLineDao headLineDao;

	//1.添加头条
	@Override
	@Transactional
	public HeadLineExcution addHeadLine(HeadLine headLine, ImageHolder thumbnail) {
		if(headLine == null){
			return new HeadLineExcution(HeadLineStateEnum.EMPTY);
		}
		headLine.setCreateTime(new Date());
		headLine.setLastEditTime(new Date());
		headLine.setEnableStatus(1);
		try{
			int effectNum = headLineDao.insertheadLine(headLine);
			if(effectNum <= 0){
				throw new HeadLineOperationException("添加头条失败！");
			}else{
				if(thumbnail.getImage()!= null){
					try {
						addHeadImg(headLine,thumbnail);// 存储图片
					} catch (Exception e) {
						throw new HeadLineOperationException("addHeadImg Exception:"+e.getMessage());
					}
					//更新头条的图片地址
					effectNum = headLineDao.updateHeadLine(headLine);
					if(effectNum <= 0){
						throw new HeadLineOperationException("添加头条图片地址失败");
					}
				}
			}
		}catch(Exception e){
			throw new HeadLineOperationException("添加头条失败！"+e.getMessage());
		}
		return new HeadLineExcution(HeadLineStateEnum.SUCCESS,headLine);
	}

	private void addHeadImg(HeadLine headLine, ImageHolder thumbnail) {
		// 获取HeadLine图片目录的相对值路径
		String dest = PathUtil.getHeadLineImagePath();
		String HeadLineImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		headLine.setLineImg(HeadLineImgAddr);
	}

	//2..根据传入的条件返回指定的头条列表
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		return headLineDao.queryHeadLine(headLineCondition);
	}



}
