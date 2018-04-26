<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<title>鏃ュ織鍒楄〃</title>
<link rel="icon" href="__PUBLIC__/spyonyou.png">
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


</head>
<body ng-app="logApp" ng-controller="logController" class="">
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
			<form class="form-horizontal col-md-12" ng-submit="log.search()">
				<div class="form-group">
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-globe"></span></span> <input ng-model="log.ip" type="text" class="form-control" placeholder="IP">
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-zoom-in"></span></span> <input ng-model="log.address" type="text" class="form-control"
								placeholder="浣嶇疆">
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-home"></span></span> <input ng-model="log.host" type="text" class="form-control" placeholder="璁块棶鍦板潃">
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-info-sign"></span></span> <input ng-model="log.agent" type="text" class="form-control"
								placeholder="鏍囪瘑">
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span> <input ng-model="log.startTime" type="text" class="form-control"
								placeholder="寮�濮嬫椂闂�">
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="input-group">
							<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span> <input ng-model="log.endTime" type="text" class="form-control"
								placeholder="缁撴潫鏃堕棿">
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<div class="col-md-6 col-xs-6">
							<div class="checkbox">
								<label> <input type="checkbox" title="鏄剧ず鏈晫闈㈣闂巻鍙�" ng-model="log.include" /> <span style="font-size: 18px;" class="glyphicon glyphicon-plus-sign"></span>
								</label>
							</div>
						</div>
						<div class="col-md-6 col-xs-6">
							<button class="btn btn-default pull-right" type="button" tilte="鍒囨崲瑙嗗浘" ng-click="log.switch=!log.switch">
								<span class="glyphicon glyphicon-retweet"></span>
							</button>
						</div>
					</div>
					<div class="col-md-3 col-xs-6">
						<button class="btn btn-default pull-right" type="submit">
							<span class="glyphicon glyphicon-search"></span> 妫�绱�
						</button>
					</div>
				</div>
			</form>
		</div>
		<div class="row" ng-hide="!log.switch">
			<div class="col-md-12">
				<div class="table-responsive">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th></th>
								<th width="*">鐢ㄦ埛IP</th>
								<th width="*">浣嶇疆</th>
								<th width="*">璁块棶鏃堕棿</th>
								<th width="*">璁块棶鍦板潃</th>
								<th width="*">鏍囪瘑</th>
								<th width="*">绫诲瀷</th>
								<th width="*">鎻忚堪</th>
								<th width="*">璇︽儏</th>
							</tr>
						</thead>
						<tbody class="data-list" style="white-space: nowrap;">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row data-list2" ng-hide="log.switch"></div>
		<nav>
			<ul class="pager">
				<li><a href="javascript:;" ng-click="log.prePage()" ng-disabled="{{log.page<=0}}">涓婁竴椤�</a></li>
				<li><input ng-model="log.page" style="width: 50px" placeholder="褰撳墠椤�"></li>
				<li><a href="javascript:;" ng-click="log.nextPage()">涓嬩竴椤�</a></li>
				<li><label>姣忛〉&nbsp;</label><input ng-model="log.rows" style="width: 30px" placeholder="琛屾暟"><label>&nbsp;琛�</label></li>
			</ul>
		</nav>
	</div>
	<script id="logList" type="text/html">
{{if rows.length>0}}
    {{each rows as value i}}
			<tr>
			<th scope="row" logid="{{value.id}}">{{i+1}}</th>
			<td>{{value.ip}}</td>
			<td>{{value.address}}</td>
			<td>{{value.visittime | dateFormat:'yy-MM-dd hh:mm:ss'}}</td>
			<td title="{{value.host}}">{{value.host}}</td>
			<td title="{{value.agent}}">{{value.agent}}</td>
			<td>{{value.request==1?"绉诲姩搴旂敤":"娴忚鍣�"}}</td>
			<td title="{{value.infos}}">{{value.infos}}</td>
			<td title="{{value.detail}}">{{value.detail}}</td>
			</tr>
    {{/each}}
{{/if}}
{{if rows.length<=0}}
			<tr>
				<td  colspan="6" class="text-center">鏆傛棤鏁版嵁...</td>
			</tr>
{{/if}}
</script>
	<script id="logList2" type="text/html">
    {{each rows as value i}}
			<div class="col-sm-4 col-md-3">
		    <div class="thumbnail">
		      <div class="caption">
		        <p>鐢ㄦ埛IP锛歿{value.ip}}</p>
				<p>浣嶇疆锛歿{value.address}}</p>
				<p>璁块棶鏃堕棿锛歿{value.visittime | dateFormat:'yy-MM-dd hh:mm:ss'}}</p>
				<p>璁块棶鍦板潃锛歿{value.host}}</p>
				<p>鏍囪瘑锛歿{value.agent}}</p>
				<p>璁块棶绫诲瀷锛歿{value.request==0?"娴忚鍣�":"绉诲姩搴旂敤"}}</p>
				<p>鎻忚堪锛歿{value.infos}}</p>
				<p>璇︽儏锛歿{value.detail}}</p>
		      </div>
		    </div>
		  </div>
    {{/each}}
</script>
	<script src="__PUBLIC__/plugins/bootstrap3/js/bootstrap.min.js"></script>
	<script src="__PUBLIC__/template.js"></script>
	<script type="text/javascript">
		var app = angular.module("logApp", []);
		app.controller("logController", function($scope) {
			$scope.log = {
				page : 1,
				rows : 20,
				data : {},
				switch:true,
				init : function() {
					this.getList();
				},
				prePage : function() {
					if (this.page <= 1) {
						return;
					}
					this.page--;
					this.getList();
				},
				nextPage : function() {
					var total = this.data.total || 0;
					if (this.page > total / this.rows) {
						return;
					}
					this.page++;
					this.getList();
				},
				search : function() {
					this.page = 1;
					this.getList();
				},
				getList : function() {
					$.ajax({
						url : "Log/find",
						type : "post",
						data : {
							ip : $scope.log.ip,
							host : $scope.log.host,
							agent : $scope.log.agent,
							address : $scope.log.address,
							startTime : $scope.log.startTime,
							endTime : $scope.log.endTime,
							include : $scope.log.include,
							page : this.page,
							rows : this.rows > 0 ? this.rows : 20
						},
						success : function(data) {
							var list = JSON.parse(data);
							$scope.log.data = list;
							$(".data-list").html(template("logList", list));
							$(".data-list2").html(template("logList2", list));
						}
					});
				}
			}
			$scope.log.init();
		});
	</script>
</body>
</html>