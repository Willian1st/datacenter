<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="spyonyou.png">
<title>摇一摇</title>
<!-- Bootstrap 核心样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap 可选主题样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- 自定义样式表 -->
<link href="signin.css" rel="stylesheet">
<!-- HTML5 shim 和 Respond.js 使IE8 支持 HTML5 元素 和 媒体查询 -->
<!--[if lt IE 9]>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/html5shiv.min.js"></script>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/respond.min.js"></script>
		<![endif]-->
</head>
<body ng-app="partyApp" ng-controller="partyController" class="center-block" style="padding-top: 50px;">
	<div class="container">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<div class="jumbotron center-block">
					<h1 class="text-center">Hello, Welcome!</h1>
					<p class="text-center init-info">点击开始</p>
					<p class="text-center init-info">摇动手机</p>
					<p class="text-center hidden start-info">开始摇吧!!</p>
					<p class="text-center">
						<button id="startShake" class="btn btn-primary btn-lg btn-success init-info">开 始</button>
					</p>
					<p class="text-center hidden start-info">
						<img id="shakeImg" src="__PUBLIC__/yearparty/shake.gif" alt="开始摇吧......">
					</p>
					<audio class="hidden" id="musicBox" preload="metadata" controls src="" autoplay="false"></audio>
				</div>
			</div>
			<div class="col-md-2"></div>
		</div>
	</div>
	<!-- /container -->
	<!-- IE10 视口 hack for Surface/desktop Windows 8 bug -->
	<script src="__PUBLIC__/plugins/bootstrap3/js/ie10-viewport-bug-workaround.js"></script>
	<script src="__PUBLIC__/angular-1.0.1.min.js"></script>
	<script src="__PUBLIC__/jquery.js"></script>
	<script src="__PUBLIC__/plugins/bootstrap3/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		var app = angular.module("partyApp", []);
		app.controller("partyController", function($scope) {
			$("#startShake").on("click", function() {
				$(".init-info").hide();
				$(".start-info").removeClass("hidden");
				init();
			})
		});
		var media = $("#musicBox");
		var shakeNum = 0;
		$('#shakeImg').popover({
			content : function() {
				return "已经摇了1次"
			},
			placement : "top"
		});
		$('#shakeImg').on('shown.bs.popover', function() {
			var obj = $(".popover-content");
			obj.text(obj.text().replace(/\d+/g, shakeNum))
		});
		var SHAKE_THRESHOLD = 2000;
		var last_update = 0;
		var x = y = z = last_x = last_y = last_z = 0;
		function init() {
			if (window.DeviceMotionEvent) {
				window.addEventListener('devicemotion', deviceMotionHandler, false);
			} else {
				console.log('not support mobile event');
			}
		}
		function deviceMotionHandler(eventData) {
			var acceleration = eventData.accelerationIncludingGravity;
			var curTime = new Date().getTime();
			if ((curTime - last_update) > 100) {
				var diffTime = curTime - last_update;
				last_update = curTime;
				x = acceleration.x;
				y = acceleration.y;
				z = acceleration.z;
				var speed = Math.abs(x + y + z - last_x - last_y - last_z) / diffTime * 10000;
				if (speed > SHAKE_THRESHOLD) {
					media.attr("src", "__PUBLIC__/yearparty/shake.mp3");
					shakeNum = shakeNum + 1;
					$('#shakeImg').popover('show');
				}
				last_x = x;
				last_y = y;
				last_z = z;
			}
		}
		$(function() {
			if (!navigator.userAgent.match(/(MicroMessenger)/i)) {
				document.write("<div style='text-align:center'>本界面只能在微信中打开(⊙o⊙)哦</div>");
			}
		});
	</script>
</body>
</html>