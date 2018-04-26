$(function() {
	var cityUrl = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js';
	$.getScript(cityUrl, function(script, textStatus, jqXHR) {
		var citytq = remote_ip_info.city;// 获取城市
		show(citytq);
		$("#defalut").html("当前城市:");
	});
	$("#search").on("click", function() {
		var city = $.trim($("#search_text").val());
		if (city != "") {
			if (/^[\u4e00-\u9fa5]+$/.test(city)) {
				if (city.indexOf('市') >= 0) {
					city = $.trim(city.replace('市', ''));
				}
				show(city);
			} else {
				confirm("只支持中文查询...");
			}

		} else {
			confirm("请输入城市...");
		}
	});
});

function show(city) {
	var url = "http://php.weather.sina.com.cn/iframe/index/w_cl.php?code=js&city="
			+ city + "&day=0&dfc=3";
	$("#defalut").html("城市:");
	$
			.ajax({
				url : url,
				dataType : "script",
				scriptCharset : "gbk",
				success : function(data) {
					if (typeof (window.SWther.w[city]) == "undefined") {
						confirm("远程服务器错误...");
						return false;
					}
					var _w = window.SWther.w[city][0];
					var _f = _w.f1 + "_0.png";// 图片
					if (new Date().getHours() > 17) {
						_f = _w.f2 + "_1.png";
					}
					var img = "<img width='16px' height='16px' src='http://i2.sinaimg.cn/dy/main/weather/weatherplugin/wthIco/20_20/"
							+ _f + "' />";
					$("#city").html(city);
					$("#status").html(img + " " + _w.s1);
					$("#temp").html(_w.t1 + " - " + _w.t2 + "℃");
					$("#wind").html(_w.d1 + " " + _w.p1 + "级");
				}
			});
}