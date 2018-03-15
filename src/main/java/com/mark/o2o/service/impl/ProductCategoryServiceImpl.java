package com.mark.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mark.o2o.dao.ProductCategoryDao;
import com.mark.o2o.dao.ProductDao;
import com.mark.o2o.dto.ProductCategoryExecution;
import com.mark.o2o.entity.ProductCategory;
import com.mark.o2o.enums.ProductCategoryStateEnum;
import com.mark.o2o.exceptions.ProductCategoryOperationException;
import com.mark.o2o.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	
	@Autowired
	private ProductDao productDao;

	/**
	 * 1.通过shopId查询该店铺商品类别
	 * @param shopId：店铺的ID
	 * @return 该店铺的商品类别
	 */
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	/**
	 * 2.批量新增商品类别
	 * @param productCategory
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	@Override
	@Transactional
	public ProductCategoryExecution batchAddProductCategory(
			List<ProductCategory> productCategoryList)
					throws ProductCategoryOperationException {
		if(productCategoryList != null && productCategoryList.size()>0){
			try{
				int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectNum <=0){//插入失败
					throw new ProductCategoryOperationException("批量店铺类别创建失败");
				}else{//插入成功
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
				}
			}catch(Exception e){
				throw new ProductCategoryOperationException("batchAddProductCategory error: " + e.getMessage());
			}
		}else{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	/**
	 * 3.将此类别下的商品里的类别id置为空，再删除掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws RuntimeException
	 */
	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)throws ProductCategoryOperationException {
		// 解除tb_product里的商品与该producategoryId的关联
		try {
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if (effectedNum < 0) {
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		} catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error: " + e.getMessage());
		}
		// 删除该productCategory
		try{
			int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum <= 0){
				throw new ProductCategoryOperationException("删除商品失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch(Exception e ){
			throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
		}
	}


}
