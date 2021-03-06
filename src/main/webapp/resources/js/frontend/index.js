$(function(){
	//定义访问后台，获取头条列表以及一级类别列表的URL
	var url ="/o2o/frontend/listmainpageinfo";
	//访问后台，获取头条列表以及一级类别列表
	$.getJSON(url,function(data){
		if(data.success){
			var headLineList = data.headLineList;//获取后台传递过来的头条列表
			var swiperHtml = '';
			headLineList.map(function(item,index){
				swiperHtml += '' + '<div class="swiper-slide img-wrap">'
				+ '<a href="' + item.lineLink
				+ '" external><img class="banner-img" src="' + item.lineImg
				+ '" alt="' + item.lineName + '"></a>' + '</div>';
			});
			$('.swiper-wrapper').html(swiperHtml);//将轮播图组赋值给前端HTML控件
			//设定轮播图轮换时间为3秒
			$(".swiper-container").swiper({
				autoplay : 3000,
				//用户对轮播图进行操作时，是否自动停止autoplay
				autoplayDisableOnInteraction : true
			});
			
			var shopCategoryList = data.shopCategoryList;//获取后台传递过来的大类列表
			var categoryHtml = '';
			//遍历大类列表，拼接出俩俩(col-50)一行的类别
			
			shopCategoryList.map(function(item, index) {
				categoryHtml += ''
						+ '<div class="col-50 shop-classify" data-category='
						+ item.shopCategoryId + '>' + '<div class="word">'
						+ '<p class="shop-title">' + item.shopCategoryName
						+ '</p>' + '<p class="shop-desc">'
						+ item.shopCategoryDesc + '</p>' + '</div>'
						+ '<div class="shop-classify-img-warp">'
						+ '<img class="shop-img" src="' + item.shopCategoryImg
						+ '">' + '</div>' + '</div>';
			});
			//将拼接好的类别赋值给前端HTML控件进行展示
			$('.row').html(categoryHtml);
		}else{
			$.toast("很抱歉，后台加载数据失败！");
		}
	});



	//点击我icon事件
	$("#me").click(function(){
		$.openPanel('#panel-right-demo');
	});
});;