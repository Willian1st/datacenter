$(function () {

	$('#box').layout({
		fit : true,
	});
	
	/*
	$(document).click(function () {
		$('#box').layout().css('display', 'block');
		$('#box').layout('resize');
	});
	
	
	//console.log($('#box').layout('panel','north'));
	//console.log($('#box').layout('collapse','north'));
	//console.log($('#box').layout('expand','north'));
	
	$('#box').layout('add', {
		title : '中间',
		region : 'center',
	});
	*/
	$('#box').layout('remove','east');
	

});



	









