package com.mark.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/frontend")
public class FrontendController {
	
	//1.首页
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	private String index() {
		return "frontend/index";
	}
	
	//2.商店列表页
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	private String showShopList(){
		return "frontend/shoplist";
	}
}
