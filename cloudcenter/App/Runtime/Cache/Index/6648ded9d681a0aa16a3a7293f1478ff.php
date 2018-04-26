<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link rel="icon" href="__PUBLIC__/spyonyou.png">
<title>谷歌卫星地图</title>
<style>
html,body {
	height: 100%;
	margin: 0;
	padding: 0;
}

#map {
	height: 100%;
}
</style>
<script async defer src="http://maps.google.cn/maps/api/js?key=AIzaSyC95uxrFpFsZsg_NRQTXhaK23M7rxVkruk&callback=initMap">
	
</script>
<script src="__PUBLIC__/jquery.js"></script>
<script src="__PUBLIC__/js/dialog.js"></script>
<link href="__PUBLIC__/css/dialog.css" rel="stylesheet">
<!-- script async defer src="http://maps.google.cn/maps/api/js?key=AIzaSyC95uxrFpFsZsg_NRQTXhaK23M7rxVkruk&callback=initMap&region=CN&language=zh-CN">
	
</script-->
</head>
<body>
	<div id="map" style="height: 100%"></div>
	<script type="text/javascript">
		var gpslocation = {
			lat : 34.205770,
			lng : 117.283939
		};
		var googlemap = null;
		function initMap() {
			getLocation();
			// Create a map object and specify the DOM element for display.
			googlemap = new google.maps.Map(document.getElementById('map'), {
				center : gpslocation,
				// Set mapTypeId to google.maps.MapTypeId.SATELLITE in order
				// to activate satellite imagery.
				mapTypeId : google.maps.MapTypeId.HYBRID,
				scrollwheel : false,
				zoom : 13,
				zoomControl : true,
				mapTypeControl : true,
				scaleControl : true,
				streetViewControl : true,
				rotateControl : true,
				mapTypeControlOptions : {
				//style : google.maps.MapTypeControlStyle.DROPDOWN_MENU
				//地图类型控件
				}
			});
		}
		function getLocation() {
			var options = {
				enableHighAccuracy : true,//是否要求高精度的地理信息
				maximumAge : 1000
			//应用程序的缓存时间
			}
			if (navigator.geolocation) {
				//浏览器支持geolocation
				navigator.geolocation.getCurrentPosition(onSuccess, onError, options);

			} else {
				//浏览器不支持geolocation
			}
		}

		//成功时
		function onSuccess(position) {
			//经度
			var longitude = position.coords.longitude;
			//纬度
			var latitude = position.coords.latitude;
			gpslocation.lng = longitude;
			gpslocation.lat = latitude;
			console.log("返回用户位置:" + gpslocation.lat + "," + gpslocation.lng);
			googlemap.setCenter(gpslocation);
			tips("位置可能有偏差");
		}

		//失败时
		function onError(error) {
			var info = "位置获取失败";
			switch (error.code) {
			case 1:
				info = "位置服务被拒绝";
				break;

			case 2:
				info = "暂时获取不到位置信息";
				break;

			case 3:
				info = "获取信息超时";
				break;

			case 4:
				info = "未知错误";
				break;
			}
			tips(info + ",使用默认位置:徐州");
		}
		function tips(info) {
			if (window.dialog) {
				var d = dialog({
					content : info,
					quickClose : true,
					fixed : true,
				// 点击空白处快速关闭
				});
				d.show();
				setTimeout(function() {
					d.close().remove();
				}, 2000);
			}
		}
	</script>
</body>
</html>