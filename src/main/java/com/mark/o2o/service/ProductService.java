package com.mark.o2o.service;

import java.util.List;

import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.dto.ProductExecution;
import com.mark.o2o.entity.Product;
import com.mark.o2o.exceptions.ProductOperationException;

public interface ProductService {
	/**
	 * 1.添加商品信息以及图片处理
	 * 
	 * @param product
	 * @param thumbnail
	 * @param productImgs
	 * @return
	 * @throws ProductOperationException
	 */
	ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws ProductOperationException;
}
