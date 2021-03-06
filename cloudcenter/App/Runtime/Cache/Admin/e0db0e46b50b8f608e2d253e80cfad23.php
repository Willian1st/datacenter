<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="__PUBLIC__/spyonyou.png">
<!-- Bootstrap 核心样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap 可选主题样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="__PUBLIC__/css/dialog.css" rel="stylesheet">
<!-- HTML5 shim 和 Respond.js 使IE8 支持 HTML5 元素 和 媒体查询 -->
<!--[if lt IE 9]>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/html5shiv.min.js"></script>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/respond.min.js"></script>
		<![endif]-->
<!-- IE10 视口 hack for Surface/desktop Windows 8 bug -->
<script src="__PUBLIC__/plugins/bootstrap3/js/ie10-viewport-bug-workaround.js"></script>
<script src="__PUBLIC__/jquery.js"></script>
<script src="__PUBLIC__/js/jquery.cookie.js"></script>
<script src="__PUBLIC__/js/dialog.js"></script>
<script src="__PUBLIC__/angular-1.0.1.min.js"></script>


<title>璁惧鍒楄〃</title>
</head>
<body ng-app="deviceApp" ng-controller="deviceController" class="center-block">
	<style>
body {
	padding-top: 70px;
}
</style>
<nav class="navbar  navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only">瀵艰埅</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"> <img alt="WG" class="img-responsive img-circle" style="width: 30px; position: relative; top: -5px;" src="__PUBLIC__/spyonyou.png">
			</a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">棣栭〉</a></li>
				<li><a href="Device">璁惧鍒楄〃</a></li>
				<li><a href="Log">鏃ュ織鍒楄〃</a></li>
				<li><a href="../GoogleMaps">鍦板浘鏈嶅姟</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#">鐢ㄦ埛涓績</a></li>
				<li><a href="../Admin/Index/logout">閫�鍑�</a></li>
			</ul>
		</div>
	</div>
</nav>

	<div class=container-fluid>
		<div class="row">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<th>璁惧ID</th>
								<th>鐢ㄦ埛璇嗗埆ID</th>
								<th>SIMID</th>
								<th>鍚姩鏃堕棿</th>
								<th>鐘舵��</th>
								<th>鐢ㄦ埛鍚�</th>
								<th>妫�鏌ユ椂闂�</th>
							</tr>
						</thead>
						<tbody class="data-list">
							<tr ng-repeat="value in device.data">
								<td scope="row">{{value.id}}</td>
								<td>{{value.deviceid}}</td>
								<td>{{value.userid}}</td>
								<td>{{value.sim}}</td>
								<td>{{value.time}}</td>
								<td>{{value.flag}}</td>
								<td>{{value.username}}</td>
								<td>{{value.checktime}}</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row data-list2"></div>
	</div>
	<script id="deviceList2" type="text/html">
    {{each rows as value i}}
			<div class="col-sm-4 col-md-3">
		    <div class="thumbnail">
		      <img src="" alt="璁板綍ID锛歿{value.id}}">
		      <div class="caption">
		        <p>璁惧ID锛歿{value.deviceid}}</p>
		        <p>鐢ㄦ埛璇嗗埆ID锛歿{value.userid}}</p>
				<p>SIMID锛歿{value.sim}}</p>
				<p>鍚姩鏃堕棿锛歿{value.time | dateFormat:'yy-MM-dd hh:mm:ss'}}</p>
				<p>
				<form onsubmit="updateDevice(this);return false;">
					<input value="{{value.id}}"  name = "id" type="hidden"/>
					<div class="form-group">
						鐘舵�侊細{{value.flag}}
						<select class="form-control" name="flag">
							<option value="1" {{if value.flag==1}}selected{{/if}}>鍏佽</option>
							<option value="0" {{if value.flag==0}}selected{{/if}}>绂佹</option>
						</select>
					</div>
					<div class="form-group">
						<label for="username">鐢ㄦ埛鍚�</label> 
						<input value="{{value.username}}"  type="text" class="form-control" id="username" name="username" placeholder="鐪熷疄濮撳悕"/>
					</div>
					<div class="form-group">
						<button class="btn btn-primary" type="submit">璁剧疆</button>
					</div>
				</form>
				<p>妫�鏌ユ椂闂达細 {{value.checktime | dateFormat:'yy-MM-dd hh:mm:ss'}}</p>
		      </div>
		    </div>
		  </div>
    {{/each}}
</script>
	<script src="__PUBLIC__/plugins/bootstrap3/js/bootstrap.min.js"></script>
	<script src="__PUBLIC__/template.js"></script>
	<script type="text/javascript">
		var app = angular.module("deviceApp", []);
		app.controller("deviceController", function($scope) {
			$scope.device = {
				init : function() {
					$scope.device.getList();
				},
				getList : function() {
					$.ajax({
						url : "Device/find",
						type : "post",
						data : {},
						success : function(data) {
							var list = JSON.parse(data);
							if (list.rows && list.rows.length > 0) {
								$scope.$apply(function() {
									$scope.device.data = list.rows;
								});
								console.log(JSON.stringify($scope.device.data));
								$(".data-list2").html(template("deviceList2", list));
							}
						}
					});
				}
			}
			$scope.device.init();
		});
		function updateDevice(obj) {
			obj = $(obj);
			$.ajax({
				url : "Device/update",
				type : "post",
				data : obj.serialize(),
				success : function(data) {
					var result = JSON.parse(data);
					var info = "娌℃湁淇敼";
					if (result == 1) {
						info = "璁剧疆鎴愬姛";
					}
					var d = dialog({
						content : info,
						quickClose : true,
						fixed : true,
					// 鐐瑰嚮绌虹櫧澶勫揩閫熷叧闂�
					});
					d.show();
					setTimeout(function() {
						d.close().remove();
					}, 2000);
				}
			});
		}
	</script>
</body>
</html>