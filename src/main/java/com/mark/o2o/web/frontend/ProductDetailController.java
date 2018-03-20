package com.mark.o2o.web.frontend;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mark.o2o.entity.Product;
import com.mark.o2o.service.ProductService;
import com.mark.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	
	//根据商品Id获取商品详情
	@RequestMapping(value="/listproductdetailpageinfo",method=RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request){
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取从前端传递过来的productId
		long productId = HttpServletRequestUtil.getLong(request, "productId");
		if(productId == -1){
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty productId");
		}else{
			Product product = productService.getProductById(productId);
			modelMap.put("success", true);
			modelMap.put("product", product);
		}
		return modelMap;
	}
}
