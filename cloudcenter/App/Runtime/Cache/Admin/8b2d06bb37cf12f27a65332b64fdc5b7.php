<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="__PUBLIC__/spyonyou.png">
<!-- Bootstrap ������ʽ�� -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap ��ѡ������ʽ�� -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="__PUBLIC__/css/dialog.css" rel="stylesheet">
<!-- HTML5 shim �� Respond.js ʹIE8 ֧�� HTML5 Ԫ�� �� ý���ѯ -->
<!--[if lt IE 9]>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/html5shiv.min.js"></script>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/respond.min.js"></script>
		<![endif]-->
<!-- IE10 �ӿ� hack for Surface/desktop Windows 8 bug -->
<script src="__PUBLIC__/plugins/bootstrap3/js/ie10-viewport-bug-workaround.js"></script>
<script src="__PUBLIC__/jquery.js"></script>
<script src="__PUBLIC__/js/jquery.cookie.js"></script>
<script src="__PUBLIC__/js/dialog.js"></script>
<script src="__PUBLIC__/angular-1.0.1.min.js"></script>


<!-- 自定义样式表 -->
<link href="__PUBLIC__/signin.css" rel="stylesheet">

<title>登录</title>
</head>
<body ng-app="loginApp" ng-controller="loginController" class="center-block">
	<div class=container-fluid>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="container col-md-6">
				<form class=" form-signin" role="form" ng-submit="login.doLogin()">
					<h2 class="form-signin-heading text-center">
						<img width="120" class="img-circle" src="__PUBLIC__/logo.jpg" alt="Logo">
					</h2>
					<div class="row">
						<div class=" col-md-12" style="margin-bottom: 20px;">
							<input class="form-control" placeholder="用户名/邮箱地址" required autofocus ng-model="username" />
						</div>
						<div class=" col-md-12">
							<input type="password" class="form-control" placeholder="密码" required ng-model="code" />
						</div>
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" ng-model="login.remember"> 记住我
						</label>
					</div>
					<button class="btn btn-primary col-md-3" type="submit">登录</button>
					<button class="btn btn-cancel col-md-3 pull-right " type="button">忘记密码</button>
				</form>
				<div class="clearfix" style="border-bottom: 1px solid #eee; margin-top: 60px;"></div>
				<ul class="list-inline" style="margin: 10px;">
					<!-- li ng-repeat="website in webSites"><p class="text-muted">{{ website }}</p></li-->
				</ul>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script type="text/javascript">
		var app = angular.module("loginApp", []);
		app.controller("loginController", function($scope, $http) {
			//$scope.webSites = [ '百度', '微博', '谷歌', '西贝', '12306' ];
			$scope.login = {
				init : function() {
					if ($scope.login.remember) {
						$scope.username = $.cookie("_username_") || "";
					} else {
						$scope.username = $.cookie("_username_", "");
					}
				},
				remember : true,
				doLogin : function() {
					$.ajax({
						url : "Admin/Index/login",
						type : "post",
						data : {
							username : $scope.username,
							code : $scope.code
						},
						success : function(data) {
							var result = JSON.parse(data);
							if (result.flag == 0) {
								if ($scope.login.remember) {
									$.cookie("_username_", $scope.username);
								} else {
									$.cookie("_username_", "");
								}
								window.location = "Admin/Log"
							} else {
								var d = dialog({
									content : result.info,
									quickClose : true,
									fixed : true,
								// 点击空白处快速关闭
								});
								d.show();
							}
						}
					});
				}
			}
			$scope.login.init();
		});
	</script>
</body>
</html>