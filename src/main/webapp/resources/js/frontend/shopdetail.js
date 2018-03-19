$(function(){
	var loading = false;
	var showAllinfo = false;
	var pageSize = 3;
	var pageIndex = 1;
	var shopId = getQueryString('shopId');
	var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId=' + shopId;//获取本店铺信息以及商品类别信息列表的URL
	var listUrl = '/o2o/frontend/listproductsbyshop';//列出商品列表的URL
	
	getSearchDivData();// 渲染出店铺基本信息以及商品类别列表以供搜索
	addItem(pageSize,pageIndex);
	
	function getSearchDivData(){
		$.getJSON(searchDivUrl,function(data){
			if(data.success){
				var shop = data.shop;
				$("#shop-cover-pic").attr('src',shop.shopImg);
				$("#shop-desc").html(shop.name);
				$("#shop-update-time").html(new Date(shop.lastEditTime).Format("yyyy-MM-dd"));
				$('#shop-addr').html(shop.shopAddr);
				$('#shop-phone').html(shop.phone);
				$('#shop-name').append(shop.shopName);
				
				var productCategoryList = data.productCategoryList;// 获取后台返回的该店铺的商品类别列表
				if(productCategoryList != 0){
					var tempHtml = '';
					productCategoryList.map(function(item,index){
						tempHtml += '<a href="#" class="button" id='+item.productCategoryId+'>'+item.productCategoryName+'</a>'
					});
					$("#shopdetail-button-div").html(tempHtml);
				}else{
					console.error("渲染商品类别列表异常:"+data.errMsg);  
					$.toast("加载失败！");
					$(".infinite-scroll-preloader").hide();
				}
				
				
				
			}else{
				console.error("渲染商店基本信息以及商品类别列表异常:"+data.errMsg);  
				$.toast("加载失败！");
				$(".infinite-scroll-preloader").hide();
			}
		});
	}
	
	function addItem(pageSize,pageIndex){
		var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
		+ pageSize + '&productCategoryId=' + productCategoryId
		+ '&productName=' + productName + '&shopId=' + shopId;
		loading = true;// 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
		$.getJSON(url,function(data){
			if(data.success){
				maxItems = data.count;//商品总数
				var html = '';
				var productList = data.productList;
				if(shopList == 0){
					$('.infinite-scroll-preloader').hide();// 隐藏提示符
					return;
				}
				productList.map(function(item,index){
					html += '' + '<div class="card" data-product-id='
					+ item.productId + '>'
					+ '<div class="card-header">' + item.productName
					+ '</div>' + '<div class="card-content">'
					+ '<div class="list-block media-list">' + '<ul>'
					+ '<li class="item-content">'
					+ '<div class="item-media">' + '<img src="'
					+ item.imgAddr + '" width="44">' + '</div>'
					+ '<div class="item-inner">'
					+ '<div class="item-subtitle">' + item.productDesc
					+ '</div>' + '</div>' + '</li>' + '</ul>'
					+ '</div>' + '</div>' + '<div class="card-footer">'
					+ '<p class="color-gray">'
					+ new Date(item.lastEditTime).Format("yyyy-MM-dd")
					+ '更新</p>' + '<span>点击查看</span>' + '</div>'
					+ '</div>';
				});
				$('.list-div').append(html);
				var total = $('.list-div .card').length;//当前店铺总数
				if(total >= maxItems){
					$('.infinite-scroll-preloader').hide();
					showAllInfo = true;
					$.toast("已加载完所有商品！");
				}else{
					$('.infinite-scroll-preloader').show();
				}
				
				loading = false;// 加载结束，可以再次加载了
				$.refreshScroller();// 刷新页面，显示新加载的店铺
			}else{
				$(".infinite-scroll-preloader").hide();
				console.error("渲染商品列表异常:"+data.errMsg);  
				$.toast("加载失败！");
			}
			
		});
	}
	
	//事件1:下滑屏幕自动进行分页搜索
	$(document).on('infinite','.infinite-scroll-bottom',function(){
		if(loading){
			return;
		}
		if(showAllInfo){
			return;
		}
		pageIndex += 1;//页数加1
		addItems(pageSize, pageIndex)
	});
	
	//事件2：选择新的商品类别之后，重置页码，清空原先的商品列表，按照新的名字去查询
})