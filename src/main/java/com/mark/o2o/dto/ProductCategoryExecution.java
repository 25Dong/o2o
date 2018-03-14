package com.mark.o2o.dto;

import java.util.List;

import com.mark.o2o.entity.ProductCategory;
import com.mark.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	private int state;//结果状态
	private String stateInfo;//状态标识
	private List<ProductCategory> productCategoryList;//结果集
	
	public ProductCategoryExecution(){
		
	}
	
	// 操作失败的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}
	
	// 操作成功的时候使用的构造器
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
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

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
	
	
}

