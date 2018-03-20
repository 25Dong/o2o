$(function(){
	var productId = getQueryString('productId');// 从地址栏的URL里获取productId
	var productUrl = '/o2o/frontend/listproductdetailpageinfo?productId='+ productId;// 获取商品信息的URL
	
	$.getJSON(productUrl,function(data){
		if(data.success){
			var product = data.product;
			$("#product-name").text(product.productName);
			$("#product-img").attr("src",product.imgAddr);
			$("product-time").text(new Date(product.lastEditTime).Format("yyyy-MM-dd"));
			$('#product-desc').text(product.productDesc);
			// 商品价格展示逻辑，主要判断原价现价是否为空 ，所有都为空则不显示价格栏目
			if(product.normalPrice != undefined && product.promotionPrice != undefined){
				$('#price').show();
				$('#normalPrice').html("<del>" + "￥" + product.normalPrice + "</del>");
				$('#promotionPrice').text('￥' + product.promotionPrice);
			}else if(product.normalPrice != undefined && product.promotionPrice == undefined){// 如果原价不为空而现价为空则只展示原价
				$('#price').show();
				$('#promotionPrice').text('￥' + product.normalPrice);
			}else if(product.normalPrice == undefined && product.promotionPrice != undefined){// 如果现价不为空而原价为空则只展示现价
				$('#promotionPrice').text('￥' + product.promotionPrice);
			}
			var imgListHtml = '';
			product.productImgList.map(function(item, index) {// 遍历商品详情图列表，并生成批量img标签
				imgListHtml += '<div> <img src="' + item.imgAddr
						+ '" width="100%" /></div>';
			});
			$('#imgList').html(imgListHtml);
		}else{
			console.error("渲染商品详情异常:"+data.errMsg);  
			$.toast("加载失败！");
		}
	});
	
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
	$.init();
});