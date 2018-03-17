$(function(){
	var loading = false;
	var showAllInfo = false;
	var parentId = getQueryString('parentId');
	var searchDivUrl = "/o2o/frontend/listshopspageinfo";// 获取店铺类别列表以及区域列表的URL
	var listUrl = '/o2o/frontend/listshops';// 获取店铺列表的URL
	var pageSize = 3;
	var pageIndex =1;//第一页
	var areaId = '';
	var shopCategoryId = '';
	var shopName = '';
	var maxItems;

	
	getSearchDivData();// 渲染出店铺类别列表以及区域列表以供搜索
	addItems(pageSize, pageIndex);//预先加载首页3条店铺信息

	//1.渲染出店铺类别列表以及区域列函数
	function getSearchDivData(){
		var getShopCategorysAndAreasUrl = searchDivUrl +"?"+"parentId="+parentId;// 如果传入了parentId，则取出此一级类别下面的所有二级类别
		$.getJSON(getShopCategorysAndAreasUrl,function(data){
			if(data.success){
				var shopCategoryList = data.shopCategoryList;//取出店铺类别信息
				var tempHtml = "";
				tempHtml += '<a href="#" class="button" data-category-id="">全部类别</a>';
				if(shopCategoryList.length != 0){
					shopCategoryList.map(function(item,index){//遍历
						tempHtml += '<a href="#" class="button" data-category-id='+item.shopCategoryId+'>'+item.shopCategoryName+'</a>';
					});
					//console.info(tempHtml);
				}
				$("#shoplist-search-div").append(tempHtml);

				var areaList = data.areaList;//取出区域信息
				var selectOption = '<option value="">全部街道</option>';
				if(areaList.length != 0){
					areaList.map(function(item,index){//遍历
						selectOption += '<option value='+item.areaId+'>'+item.areaName+'</option>'; 
					});
					//console.info(selectOption);
				}
				$("#area-search").html(selectOption);
			}else{
				console.error("渲染商店种类和区域信息异常:"+data.errMsg);  
				$.toast("加载失败！");
				$(".infinite-scroll-preloader").hide();
			}
		});
	}

	//2.渲染店铺信息函数
	function addItems(pageSize,pageIndex){
		console.info("正在请求第"+pageIndex+"页");
		//拼接组合查询的URL，赋空值默认就去掉这个条件的限制，有值就代表按这个条件去查询
		var url = listUrl+'?'+'pageIndex='+pageIndex+'&pageSize='+pageSize
		+'&parentId='+parentId+'&areaId='+areaId+"&shopCategoryId="+shopCategoryId
		+'&shopName='+shopName;
		loading = true;// 设定加载符，若还在后台取数据则不能再次访问后台，避免多次重复加载
		$.getJSON(url,function(data){
			if(data.success){
				maxItems = data.count;//店铺总数
				var html = '';
				var shopList = data.shopList;
				if(shopList == 0){
					$("#shopIsNull").show();
					$('.infinite-scroll-preloader').hide();// 隐藏提示符
					return;
				}
				shopList.map(function(item,index){//遍历
					html +=''
						+'<div class="card" data-shop-id='+item.shopId+'>'
						+'<div class="card-header">'+item.shopName+'</div>'+
						+'<div class="card-content">'
						+'<div class="list-block media-list">'
						+'<ul>'
						+'<li class="item-content">'
						+'<div class="item-media">'
						+'<img src='+item.shopImg+' width="44">'
						+'</div>'
						+'<div class="item-inner">'
						+'<div class="item-subtitle">'+item.shopDesc+'</div>'
						+'</div>'
						+'</li>'
						+'</ul>'
						+'</div>'
						+'</div>'
						+'<div class="card-footer">'
						+'<span>'+new Date(item.lastEditTime).Format("yyyy-MM-dd")+'更新</span>'
						+'<span>点击查看</span>'
						+'</div>'
						+'</div>';
				});
				$(".shop-list").append(html);//追加
				var shopCountNow = $('.list-div .card').length;//当前商店总数
				if(shopCountNow >= maxItems){
					$('.infinite-scroll-preloader').hide();// 隐藏提示符
					showAllInfo = true;
					$.toast("已加载完所有商店！");
				}else{
					$('.infinite-scroll-preloader').show();
				}

				loading = false;// 加载结束，可以再次加载了
				$.refreshScroller();//容器发生改变,如果是js滚动，需要刷新滚动
			}else{
				$(".infinite-scroll-preloader").hide();
				console.error("渲染商店列表异常:"+data.errMsg);  
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

	//事件2：选择新的店铺类别之后，重置页码，清空原先的店铺列表，按照新的类别去查询
	$("#shoplist-search-div").on('click','.button',function(e){
		if(parentId){// 如果传递过来的是一个父类下的子类
			shopCategoryId = e.target.dataset.categoryId;//二级店铺种类
			if($(e.target).hasClass('button-fill')){// 若之前已选定了别的category,则移除其选定效果，改成选定新的
				$(e.target).removeClass('button-fill');
				shopCategoryId = '';
			}else{
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			$(".list-div").empty();// 由于查询条件改变，清空店铺列表再进行查询
			pageIndex = 1;
			addItems(pageSize, pageIndex);
			
		}else{//parentId="";
			parentId = e.target.dataset.categoryId;//当前的店铺种类id为parentId
			if($(e.target).hasClass('button-fill')){// 若之前已选定了别的category,则移除其选定效果，改成选定新的
				$(e.target).removeClass('button');
				parentId='';
			}else{
				$(e.target).addClass('button-fill').siblings().removeClass('button-fill');
			}
			$('.list-div').empty();//清空所有商店信息
			pageIndex = 1;//重置页码
			addItems(pageSize, pageIndex);//加载二级店铺
		}

	});
	
	//事件3:需要查询的店铺名字发生变化后，重置页码，清空原先的店铺列表，按照新的名字去查询
	$("#search").on('change',function(e){
		shopName = e.target.value;
		$('.list-div').empty();//清空所有商店信息
		pageIndex = 1;//重置页码
		addItems(pageSize, pageIndex);//加载组合店铺名搜索
	});
	
	//事件4:区域信息发生变化后，重置页码，清空原先的店铺列表，按照新的区域去查询
	$("#area-search").on('',function(e){
		areaId =  $('#area-search').val();
		$('.list-div').empty();//清空所有商店信息
		pageIndex = 1;//重置页码
		addItems(pageSize, pageIndex);//加载组合地区搜素得到的店铺
	});
	// 点击后打开右侧栏
	$('#me').click(function() {
		$.openPanel('#panel-right-demo');
	});
	
	$("#shopIsNull").hide();
	// SUI Mobile初始化页面
	$.init();
});
