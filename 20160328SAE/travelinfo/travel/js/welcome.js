$(function() {
	$("#toNew").on("click", function() {
		location.href = "new/";
	});
	$("#bus_info")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/travel.php"
								: "travel/travel");
					});
	$("#ip_info")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/ip.php"
								: "travel/ip");
					});
	$("#weather_info")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/weather.php"
								: "travel/weather");
					});
	$("#color-info")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/color.php"
								: "travel/color");
					});
	$("#option")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/option.php"
								: "travel/option");
					});
	$("#position")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/position.php"
								: "travel/position");
					});
	$("#url")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "php/converter.php"
								: "php/converter");
					});
	$("#rmb")
			.on(
					'click',
					function() {
						window.location.href = (localStorage.local == "0" ? "travel/rmb.php"
								: "travel/rmb");
					});
	$("#trans").on('click', function() {
		window.location.href = "travel/translate.html";
	});
	$("#view").on('click', function() {
		window.location.href = "travel/view/view.html";
	});

	setInterval(showTime, 1000);
	$("#logo").attr("src", 'travel/img/logo.png');
});
function showTime() {
	var today = new Date();
	var weekday = [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ];
	/* var y = today.getFullYear() + "年"; */
	var month = today.getMonth() + 1 + "月";
	var td = today.getDate() + "日";
	var d = weekday[today.getDay() - 1];
	var h = today.getHours();
	var m = today.getMinutes();
	var s = today.getSeconds();
	/* $("#Y").text(y); */
	$("#MH").text(month);
	$("#TD").text(td);
	$("#D").text(d);
	$("#H").text(h);
	$("#M").text(m < 10 ? ("0" + m) : m);
	$("#S").text(s < 10 ? ("0" + s) : s);
	$("#real-time").fadeIn();
}

$(function() {
	// 页面浮动面板
	$("#floatPanel a.arrow").eq(0).click(function() {
		$("html,body").animate({
			scrollTop : 0
		}, 300);
		return false;
	});
	$("#floatPanel a.arrow").eq(1).click(function() {
		$("html,body").animate({
			scrollTop : $(document).height()
		}, 300);
		return false;
	});

	var panel = $(".popPanel");
	var w = panel.outerWidth();

	$(".qrcode").hover(function() {
		panel.css("width", "0px").show();
		panel.animate({
			"width" : w + "px"
		}, 300);
	}, function() {
		panel.animate({
			"width" : "0px"
		}, 300);
	});

});
