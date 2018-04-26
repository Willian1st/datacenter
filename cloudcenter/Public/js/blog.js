var app = angular.module("blogApp", []);
app.controller("blogCtrl", function($scope, $http) {
	// 请求文章详情
	$http({
		method : 'POST',
		data : $.param({
			id : getQueryString("id"),
		}),
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
		},
		url : "../php/new/getnotedetail.php"
	}).success(function(response, status, headers, config) {
		$scope.noteDetail = response;
	}).error(function(response, status, headers, config) {
		alert(response);
	});
});

app.filter('html', [ '$sce', function($sce) {
	return function(text) {
		return $sce.trustAsHtml(text);
	}
} ]);

function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
