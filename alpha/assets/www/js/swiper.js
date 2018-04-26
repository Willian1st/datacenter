$(function() {
	var mySwiper = new Swiper('.swiper-container', {
		mode : 'horizontal',// 水平
		loop : true,// 循环
		// speed : 1000,// 持续1s的时间
		// autoplay : 1000,// 隔1s自动播放
		pagination : '.pagination',
		paginationClickable : true,
	});
	$('.arrow-left').bind('click', function(e) {
		e.preventDefault();
		mySwiper.swipePrev();
	});
	$('.arrow-right').bind('click', function(e) {
		e.preventDefault();
		mySwiper.swipeNext();
	});

	var mySwiper2 = new Swiper('#swiper2', {
		mode : 'vertical',// 水平
		loop : true,// 循环
		speed : 1000,// 持续1s的时间
		autoplay : 1000,// 隔1s自动播放
	});
});