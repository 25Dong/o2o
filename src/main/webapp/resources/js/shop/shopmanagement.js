$(function(){
	var shopId = getQueryString('shopId');
	var shopInforUrl = '/o2o/shopadmin/getshopmanagementinfo?shopId=' + shopId;
	$.getJSON(shopInforUrl, function(data) {
		if (data.redirect) {
			window.location.href = data.url;
		} else {
			if (data.shopId != undefined && data.shopId != null) {
				shopId = data.shopId;
			}
			$('#shopInfo')
					.attr('href', '/o2o/shopadmin/shopoperation?shopId=' + shopId);
		}
	});
});