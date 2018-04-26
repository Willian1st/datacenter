<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="spyonyou.png">
<title>登录</title>
<!-- Bootstrap 核心样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap 可选主题样式表 -->
<link href="__PUBLIC__/plugins/bootstrap3/css/bootstrap-theme.min.css" rel="stylesheet">
<!-- 自定义样式表 -->
<link href="__PUBLIC__/signin.css" rel="stylesheet">
<!-- HTML5 shim 和 Respond.js 使IE8 支持 HTML5 元素 和 媒体查询 -->
<!--[if lt IE 9]>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/html5shiv.min.js"></script>
		  <script src="__PUBLIC__/plugins/bootstrap3/js/respond.min.js"></script>
		<![endif]-->
</head>
<body ng-app="loginApp" ng-controller="loginController" class="center-block" style="margin-top: 100px;">
	<div class="row">
		<div class="col-md-3"></div>
		<div class="container col-md-6">
			<form class=" form-signin" role="form">
				<h2 class="form-signin-heading text-center">欢迎登陆</h2>
				<div class="row">
					<div class=" col-md-12" style="margin-bottom: 20px;">
						<input class="form-control" placeholder="用户名/邮箱地址" required autofocus ng-model="loginName" />
					</div>
					<div class=" col-md-12">
						<input type="password" class="form-control" placeholder="密码" required ng-model="loginPassword" />
					</div>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me"> 记住我
					</label>
				</div>
				<button class="btn btn-primary col-md-3" type="submit">登录</button>
				<button class="btn btn-cancel col-md-3 pull-right ">忘记密码</button>
			</form>
			<div class="clearfix" style="border-bottom: 1px solid #eee; margin-top: 60px;"></div>
			<ul class="list-inline" style="margin: 10px;">
				<li ng-repeat="website in webSites"><p class="text-muted">{{ website }}</p></li>
			</ul>
		</div>
		<div class="col-md-3"></div>
	</div>
	<!-- /container -->
	<!-- IE10 视口 hack for Surface/desktop Windows 8 bug -->
	<script src="__PUBLIC__/plugins/bootstrap3/js/ie10-viewport-bug-workaround.js"></script>
	<script src="__PUBLIC__/angular-1.0.1.min.js"></script>
	<script type="text/javascript">
		var app = angular.module("loginApp", []);
		app.controller("loginController", function($scope) {
			$scope.webSites = [ '百度', '微博', '谷歌', '西贝', '12306' ];
			$scope.loginPassword = '';
		});
	</script>
</body>
</html>