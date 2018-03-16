package com.mark.o2o.web.frontend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.o2o.dao.HeadLineDao;
import com.mark.o2o.dao.ShopCategoryDao;
import com.mark.o2o.entity.HeadLine;
import com.mark.o2o.entity.ShopCategory;

@Controller
@RequestMapping("/frontend")
public class MainPageController {
	@Autowired
	private ShopCategoryDao  shopCategoryDao;
	@Autowired
	private HeadLineDao headLineDao;

	@RequestMapping(value="/listmainpageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listMainPageInfo(){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<ShopCategory> shopCategorieList = new ArrayList<ShopCategory>();
		try{
			//获取一级店铺列表（parentId为空的ShopCategory）
			shopCategorieList = shopCategoryDao.queryShopCategory(null);
			modelMap.put("shopCategorieList", shopCategorieList);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		List<HeadLine> headLineList = new ArrayList<HeadLine>();
		try{
			// 获取状态为可用(1)的头条列表
			HeadLine headLineCondition = new HeadLine();
			headLineCondition.setEnableStatus(1);
			headLineList = headLineDao.queryHeadLine(headLineCondition);
			modelMap.put("headLineList", headLineList);
		}catch(Exception e){
			modelMap.put("success", false);
			modelMap.put("errMsg", e.getMessage());
			return modelMap;
		}
		modelMap.put("success", false);
		return modelMap;

	}
}
