package com.mark.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mark.o2o.entity.HeadLine;

public interface HeadLineDao {
	
	/**
	 * 1.1插入头条
	 * @param headLine
	 * @return
	 */
	int insertheadLine(HeadLine headLine);
	
	/**
	 * 1.2更新头条信息
	 * @param headLine
	 * @return
	 */
	int updateHeadLine(HeadLine headLine);
	
	/**
	 * 2.根据传入的查询条件(头条名查询头条)
	 * @param headLineCondition
	 * @return
	 */
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
	
	
}
