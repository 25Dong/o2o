package com.mark.o2o.util;

public class PathUtil {
	private static String seperator = System.getProperty("file.separator");//获取文件的分流符号

	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "D:/projectdev/image";
		} else {
			basePath = "/Users/baidu/work/image";
		}
		basePath = basePath.replace("/", seperator);
		return basePath;
	}

	public static String getShopImagePath(long shopId) {
		String imagePath = "/upload/images/item/shop/" + shopId + "/";
		return imagePath.replace("/", seperator);
	}

	//头条图片路径	
	public static String getHeadLineImagePath(){
		String imagePath = "/upload/images/item/headline/";
		return imagePath.replace("/", seperator);
	}
	public static void main(String[] args){
		String basePath = PathUtil.getImgBasePath();
		System.out.println(basePath);//D:\projectdev\image

	}
}
