package com.mark.o2o.util;

public class PageCalculator {
	/**
	 * 
	 * @param pageIndex：当年的页数
	 * @param pageSize：页的大小
	 * @return 在数据库中的行数
	 */
	public static int calculateRowIndex(int pageIndex,int pageSize){
		//如果pageIndex = 1（第一页）：那么从行数为0开始查询，查询的条数为pageSize（页的大小）
		return (pageIndex>0)?(pageIndex - 1)*pageSize:0;
	}
}
