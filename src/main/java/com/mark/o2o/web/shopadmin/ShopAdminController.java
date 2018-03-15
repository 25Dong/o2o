package com.mark.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 主要用来解析路由并转发到相应的HTML文件中
 * @author msi
 *返回的字符串和是html页面在web-inf下的shop文件夹shopoperation.html String-web.xml的第三点配置
 */
@Controller
@RequestMapping(value = "shopadmin")
public class ShopAdminController {
	// 1.转发至店铺注册/编辑页面
	@RequestMapping(value = "/shopoperation",method=RequestMethod.GET)
	public String shopOperation(){
		return "shop/shopoperation";
	}
	
	// 2.转发至店铺列表页面
	@RequestMapping(value = "/shoplist",method=RequestMethod.GET)
	public String shoplist(){
		return "shop/shoplist";
	}
	
	// 3.转发至店铺管理页面
	@RequestMapping(value = "/shopmanagement",method=RequestMethod.GET)
	public String shopManagement(){
		return "shop/shopmanagement";
	}
	
	// 4.转发至商品类别管理页面
	@RequestMapping(value = "/productcategorymanagement",method=RequestMethod.GET)
	public String productCategoryManage(){
		return "shop/productcategorymanagement";
	}
	
	//5.装发至商品编辑/添加页面
	@RequestMapping(value = "/productoperation",method=RequestMethod.GET)
	public String productOperation(){
		return "shop/productoperation";
	}
	
	//6.// 转发至商品管理页面
	@RequestMapping(value = "/productmanagement",method=RequestMethod.GET)
	public String productManagement(){
		return "shop/productmanagement";
		
	}
}
