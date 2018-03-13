package com.mark.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 主要用来解析路由并转发到相应的HTML文件中
 * @author msi
 *
 */
@Controller
@RequestMapping(value = "shopadmin")
public class ShopAdminController {
	// 转发至店铺注册/编辑页面
	@RequestMapping(value = "/shopoperation",method=RequestMethod.GET)
	public String shopOperation(){
		//返回的字符串和是html页面在web-inf下的shop文件夹shopoperation.html String-web.xml的第三点配置
		return "shop/shopoperation";
	}
	
	// 转发至店铺列表页面
	@RequestMapping(value = "/shoplist",method=RequestMethod.GET)
	public String shoplist(){
		return "shop/shoplist";
	}
	
	// 转发至店铺管理页面
	@RequestMapping(value = "/shopmanagement",method=RequestMethod.GET)
	public String shopManagement(){
		return "shop/shopmanagement";
	}
}
