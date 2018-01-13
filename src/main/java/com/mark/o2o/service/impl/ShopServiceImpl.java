package com.mark.o2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mark.o2o.dao.ShopDao;
import com.mark.o2o.dto.ImageHolder;
import com.mark.o2o.dto.ShopExecution;
import com.mark.o2o.entity.Shop;
import com.mark.o2o.enums.ShopStateEnum;
import com.mark.o2o.exceptions.ShopOperationException;
import com.mark.o2o.service.ShopService;
import com.mark.o2o.util.ImageUtil;
import com.mark.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	
	public ShopExecution getShopList(Shop shopCondition, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	//根据shopId查找店铺
	public Shop getByShopId(long shopId) {
		return shopDao.queryByShopId(shopId);
	}

	//更新店铺信息
	public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException {
		if(shop == null || shop.getShopId() == null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}else{
			//判断是否更新图片
			try{
				if(thumbnail.getImage()!= null&&thumbnail.getImageName()!=null&&!(thumbnail.getImageName()).equals("")){
					Shop tempshop = shopDao.queryByShopId(shop.getShopId());
					//如果原来有保存图片路径
					if(tempshop.getShopImg()!=null){
						ImageUtil.deleteFileOrPath(tempshop.getShopImg());
					}
					addShopImg(shop, thumbnail);
				}
				//更新店铺信息
				shop.setLastEditTime(new Date());//更新最后一次编辑的时间
				int effectedNum = shopDao.updateShop(shop);
				if(effectedNum <= 0){
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else{
					shop = shopDao.queryByShopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS,shop);
				}
			}catch(Exception e){
				throw new ShopOperationException("modifyShop error:"+e.getMessage());
			}
		}
	}
	
	//增加店铺
	@Transactional
	public ShopExecution addShop(Shop shop, ImageHolder thumbnail)
			throws ShopOperationException {
		// 空值判断
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (thumbnail.getImage() != null) {
					// 存储图片
					try {
						addShopImg(shop, thumbnail);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, ImageHolder thumbnail) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		shop.setShopImg(shopImgAddr);
	}

}
