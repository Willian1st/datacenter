<?php
include ("../php/log4all.php");
?>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<style type="text/css">
#allmap {
	width: 100%;
	height: 100%;
	overflow: hidden;
	margin: 0;
}

#golist {
	display: none;
}

@media ( max-device-width : 780px) {
	#golist {
		display: block !important;
	}
}
</style>
<link rel="stylesheet" href="../plugins/jquery.mobile.css">
<script src="../plugins/jquery.js"></script>
<script src="../plugins/jquery.mobile.js"></script>
<!-- <script type="text/javascript" src="js/is_mobile.js"></script> -->
<script type="text/javascript"
	src="http://api.map.baidu.com/api?type=quick&ak=8RmKQQNNTQxRtd773iIPBIIz&v=1.0"></script>
</head>
<script type="text/javascript">
	var int = setInterval("geolog()", 2000);
	// 百度地图API功能
	var map = new BMap.Map("allmap"); // 创建Map实例
	var point = new BMap.Point(116.404, 39.915); // 创建点坐标
	map.centerAndZoom(point, 15); // 初始化地图,设置中心点坐标和地图级别。
	map.addControl(new BMap.ZoomControl()); //添加地图缩放控件
	function geolog() {
		var lon = $("#longitude").html();
		var lan = $("#latitude").html();
		if (!isNaN(lan) && !isNaN(lon)) {
			//clearInterval(int);
		} else {
			return;
		}
		var geo = 'null';
		$.ajax({
			url : "http://api.map.baidu.com/geocoder/v2/",
			type : "get",
			data : {
				ak : "8RmKQQNNTQxRtd773iIPBIIz",
				//callback : "renderReverse",//回调函数
				location : lan + "," + lon,
				output : "json",
				pois : "0"//1:周边100米
			},
			dataType : 'JSONP',
			success : function(data) {
				if (data.status == '0') {
					clearInterval(int);
					geo = JSON.stringify(data);
					$.ajax({
						url : "../php/sae_geolog.php",
						type : "get",
						data : {
							geo : geo,
						},
						dataType : 'JSONP'
					});
				}

			}
		});
	}

	var x = $("#list");
	var id;
	function getLocation() {
		if (navigator.geolocation) {
			//返回用户的当前位置，并继续返回用户移动时的更新位置
			id = navigator.geolocation.watchPosition(showPosition, showError);
			$("#tips").html("正在实时获取位置信息...");
		} else {
			$("#tips").html("该浏览器不支持获取地理位置。");
		}
		geolog();
	}
	function clearWatch() {
		if (navigator.geolocation) {
			navigator.geolocation.clearWatch(id);
			$("#tips").html("获取位置已停止!");
		} else {
			$("#tips").html("该浏览器不支持地理位置。");
		}
	}
	function showPosition(position) {
		$("#longitude").html(position.coords.longitude);
		$("#latitude").html(position.coords.latitude);
		$("#accuracy").html(position.coords.accuracy);
		$("#altitude").html(position.coords.altitude);
		$("#altitudeAccuracy").html(position.coords.altitudeAccuracy);
		$("#heading").html(position.coords.heading);
		$("#speed").html(position.coords.speed);
		$("#timestamp").html(dateToComm(position.timestamp));
	}
	function showError(error) {
		switch (error.code) {
		case error.PERMISSION_DENIED:
			$("#tips").html("获取地理位置的请求被拒绝。");
			break;
		case error.POSITION_UNAVAILABLE:
			$("#tips").html("位置信息是不可用的。");
			break;
		case error.TIMEOUT:
			$("#tips").html("请求用户地理位置超时。");
			break;
		case error.UNKNOWN_ERROR:
			$("#tips").html("未知错误。");
			break;
		}
	}
	function dateToComm(date) {
		var date = new Date(date);
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var date1 = date.getDate();
		var hour = date.getHours();
		var minutes = date.getMinutes();
		var second = date.getSeconds();
		return year + '年' + month + '月' + date1 + '日' + hour + '时' + minutes
				+ '分' + second + '秒';
	}
	function goToMap() {
		location.href = "maps.php"
	}
</script>
<title>Manage</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>位置信息</h1>
		</div>
		<ul id="list" data-role="listview" data-inset="true">
			<li data-role="list-divider"><span id="defalut">提示</span><span
				id="tips" class="ui-li-count">-</span></li>
			<li>经度: <span id="longitude" class="ui-li-count">-</span></li>
			<li>纬度:<span id="latitude" class="ui-li-count">-</span></li>
			<li>位置精度: <span id="accuracy" class="ui-li-count">-</span></li>
			<li>海拔: <span id="altitude" class="ui-li-count">-</span></li>
			<li>海拔精度:<span id="altitudeAccuracy" class="ui-li-count">-</span></li>
			<li>方向: <span id="heading" class="ui-li-count">-</span></li>
			<li>速度: <span id="spead" class="ui-li-count">-</span></li>
			<li>响应时间: <span id="timestamp" class="ui-li-count">-</span></li>
		</ul>
		<button onclick="getLocation()">点我开始</button>
		<button onclick="clearWatch()">点我停止</button>
		<button onclick="goToMap()">查看我在地图上的位置</button>
		<div id="allmap"></div>
		<div data-role="footer" data-position="fixed">
			<h1>请允许位置共享...</h1>
		</div>
	</div>
</body>
</html>
