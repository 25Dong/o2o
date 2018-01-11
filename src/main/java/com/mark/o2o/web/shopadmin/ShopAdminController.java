package com.mark.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "shopadmin")
public class ShopAdminController {
	
	@RequestMapping(value = "/shopoperation",method=RequestMethod.GET)
	public String shopOperation(){
		// 转发至店铺注册/编辑页面
		//返回的字符串和是html页面在web-inf下的shop文件夹shop.html String-web.xml的第三点配置
		System.out.println("2222");
		return "shop/shop";
	}
}
