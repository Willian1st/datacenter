var app = angular.module("indexApp", []);
app.controller("indexCtrl", function($scope, $http) {
	// 当前时间
	$scope.currentTime = new Date().valueOf();
	// 检索提示
	$scope.indexSearch = "";
	// 功能列表
	$scope.functionList = [ {
		url : "blog.html",
		value : "博客",
	}, {
		url : "editor.html",
		value : "笔记",
	}, {
		url : "dashboard.html",
		value : "控制台",
	}, {
		url : "",
		value : "网址导航",
	} ];
	// 请求最新的文章列表
	$http({
		method : 'POST',
		data : $.param({
			page : 1,
			rows : 12,
			sort : 'changetime',
			order : 'desc',
		}),
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		},
		url : "../php/new/getnotelist.php"
	}).success(function(response, status, headers, config) {
		$scope.noteList = response.rows;
	}).error(function(response, status, headers, config) {
		alert(response);
	});
	$scope.urlsShow = true;
});
app.filter("active", function() {
	// 过滤器
	var filterfun = function($index) {
		// 默认为第一个增加样式
		if ($index == 0) {
			return "active";
		}
		return "";
	};
	return filterfun;
});
app.filter('html', [ '$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	}
} ]);
app.filter("html2str", function() {
	return function(text) {
		text = text.replace(/<[^>]+>/g, "");// 去掉所有的html标记
		text = text.replace(/\s/g, "");// 去除所有空格
		text = text.replace(/&nbsp;/ig, "");
		return text;
	};
});
