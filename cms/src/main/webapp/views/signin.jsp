<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<jsp:include page="common/header.jsp"></jsp:include>
<!-- 自定义样式表 -->
<link href="css/signin.css" rel="stylesheet">
<title>${setting.name}-后台登录</title>
</head>
<body ng-app="loginApp" ng-controller="loginController" class="center-block">
	<div class=container-fluid>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="container col-md-6">
				<form class=" form-signin" role="form" ng-submit="login.doLogin()">
					<h2 class="form-signin-heading text-center">
						<img width="120" class="img-circle" src="${setting.picture}" alt="Logo">
					</h2>
					<div class="row">
						<div class=" col-md-12" style="margin-bottom: 20px;">
							<input class="form-control" placeholder="用户名/邮箱地址" required autofocus ng-model="username" />
						</div>
						<div class=" col-md-12">
							<input type="password" class="form-control" placeholder="密码" required ng-model="code" />
						</div>
					</div>
					<div class="checkbox hidden">
						<label> <input type="checkbox" ng-model="login.remember"> 记住我
						</label>
					</div>
					<button class="btn btn-primary col-md-3" type="submit">登录</button>
					<button class="btn btn-cancel col-md-3 pull-right" type="reset">清空</button>
				</form>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	<script type="text/javascript">
		var app = angular.module("loginApp", []);
		app.controller("loginController", function($scope, $http) {
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
					var data = {
						username : $scope.username,
						password : $scope.code
					}
					$.ajax({
						url : "/cms/signin",
						type : "post",
						contentType : 'application/json',
						dataType : "json",
						data : JSON.stringify(data),
						success : function(response) {
							if (response.result == -100) {
								layer.msg("用户名不存在");
							} else if (response.result == -200) {
								layer.msg("密码不正确");
							} else {
								layer.msg("登陆成功");
								window.location = "/cms/goods/manage"
							}
						},
						error : function(data) {
							layer.msg("登陆失败");
						}
					});
				}
			}
			$scope.login.init();
		});
	</script>
</body>
</html>
