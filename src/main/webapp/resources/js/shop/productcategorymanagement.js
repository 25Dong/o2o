$(function(){
	var listUrl = '/o2o/shopadmin/getproductcategorylist'//显示列表的URL
	var addUrl = '/o2o/shopadmin/addproductcategorys';//提交新增的URL
	var deleteUrl = '/o2o/shopadmin/removeproductcategory';//删除的URL
	//获取商品的种类
	getList();
	function getList(){
		$.getJSON(listUrl,function(data){
			if(data.success){
				var dataList = data.data;
				$('.category-warp').html('');
				var tempHtml = '';
				dataList.map(function(item,index){
					tempHtml += ''
						+ '<div class="row row-product-category now">'
						+ '<div class="col-33 product-category-name">'
						+ item.productCategoryName
						+ '</div>'
						+ '<div class="col-33">'
						+ item.priority
						+ '</div>'
						+ '<div class="col-33"><a href="#" class="button delete" data-id="'
						+ item.productCategoryId
						+ '">删除</a></div>'
						+ '</div>';
				});
				$('.category-wrap').append(tempHtml);
			}

		});
	}
	
	//新增按钮的点击事件,点击一次生成一行，并且其中的一个class属性为temp,这里的productCategory的shopId由后台赋值
	$('#new').click(function(){
		var temHtml = '<div class="row row-product-category temp">'
							+'<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"></div>'
							+'<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"></div>'
							+'<div class="col-33"><a href="#" class="button delete">删除</a></div>'
						+'</div>';
		$('.category-wrap').append(temHtml);
	});
	
	//提交按钮的点击事件
	$("#submit").click(function(){
		var tempArr=$(".temp");
		var productCategoryList = [];
		tempArr.map(function(index,item){//遍历
			var tempObj = {};
			tempObj.productCategoryName = $(item).find(".category").val();
			tempObj.priority=$(item).find('.priority').val();
			if(tempObj.productCategoryName && tempObj.priority){
				productCategoryList.push(tempObj);
			}
		});
		$.ajax({
			url : addUrl,
			type : 'POST',
			data : JSON.stringify(productCategoryList),
			contentType : 'application/json',
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
					getList();
				} else {
					$.toast('提交失败！');
				}
			}
		});
	});

	//删除
	//1.删除前端新增的种类
	$('.category-wrap').on('click','.row-product-category.temp .delete',function(e){
		console.log($(this).parent().parent());
		$(this).parent().parent().remove();
	});
	//2.删除后台数据库中的数据
	$('.category-wrap').on('click','.row-product-category.now .delete',function(e){

		var target = e.currentTarget;
		$.confirm('确定么?', function() {
			$.ajax({
				url : deleteUrl,
				type : 'POST',
				data : {
					productCategoryId : target.dataset.id
				},
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.toast('删除成功！');
						getList();
					} else {
						$.toast('删除失败！');
					}
				}
			});
		});
	
	});

});
