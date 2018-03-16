package com.mark.o2o.dto;

import java.util.List;

import com.mark.o2o.entity.HeadLine;
import com.mark.o2o.enums.HeadLineStateEnum;

public class HeadLineExcution {
	//结果状态
	private int state;
	//状态信息
	private String stateInfo;
	//头条的数量
	private int count;
	//增删改时用到的单个头条
	private HeadLine headLine;
	//批量查询时用到的头条列表
	private List<HeadLine> headLineList;
	
	public HeadLineExcution(){}
	
	//头条操作失败时的构造器
	public HeadLineExcution(HeadLineStateEnum stateEnum){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	//单个头条操作成功时的构造器
	public HeadLineExcution(HeadLineStateEnum stateEnum,HeadLine headLine){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.headLine = headLine;
	}
	
	//头条列表操作成功的构造器
	public HeadLineExcution(HeadLineStateEnum stateEnum,List<HeadLine> headLineList){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.headLineList = headLineList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public HeadLine getHeadLine() {
		return headLine;
	}

	public void setHeadLine(HeadLine headLine) {
		this.headLine = headLine;
	}

	public List<HeadLine> getHeadLineList() {
		return headLineList;
	}

	public void setHeadLineList(List<HeadLine> headLineList) {
		this.headLineList = headLineList;
	}
	
	
}
