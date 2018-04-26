var app = angular.module("goodsApp", []);
app.controller("goodsIndex", function($scope) {
	$scope.goodsIndex = {
			data:[],
		init : function() {
			$scope.goodsIndex.getList();
			// 筛选
			$("#goodsTab li").on("click",function(e){
				$scope.goodsIndex.page.pageNumber=1;
				$scope.$apply(function() {
					$scope.goodsIndex.data=[];
				});
				var order=$(this).attr("order");
				$('[name="order"]').val(order);
				$scope.goodsIndex.getList();
			});
			var classType=["label-primary","label-success","label-info","label-warning","label-danger"];
			$('#goodsType span:eq(0)').each(function(i,v){
				$(this).addClass(classType[parseInt(classType.length*Math.random())]);
			});
			// 分类
			$('#goodsType span').on("click",function(){
				var obj=this;
				$(obj).addClass(classType[parseInt(classType.length*Math.random())]);
				$.each(classType,function(i,v){
					$('#goodsType span').not(obj).removeClass(v);
				});
				$scope.goodsIndex.page.pageNumber=1;
				$scope.$apply(function() {
					$scope.goodsIndex.data=[];
				});
				var type=$(this).attr("type");
				$('[name="type"]').val(type);
				$scope.goodsIndex.getList();
			});
			$(window).scroll(function(){  
				var documentHeight=$(document).height();
				var scrollTop=$(this).scrollTop();
				var height=$(this).height();
				var offset=documentHeight- scrollTop - height;
			    if ( offset<=10){
			    	$scope.goodsIndex.page.pageNumber+=1;
			    	$scope.goodsIndex.getList();
			    }
			});
			if($("[name='manage']").val()=="true"){
				$("body").on("click",".thumbnail img",function(){
					var object=$(this);
					location.href="/cms/goods/update/"+object.attr("goodsid");
				});
				$("body").on("click",".good-remove",function(){
					var object=$(this);
					layer.confirm("确定删除吗？",function(){
						var index = layer.load(2);
						$.ajax({
							url : "/cms/goods/del/"+object.attr("goodsid"),
							type : "delete",
							success : function(data) {
								layer.close(index);
								if(data==1){
									layer.msg("删除成功");
									$scope.goodsIndex.getList(1);
								}else{
									layer.msg("删除失败");
								}
							},
							error : function(data) {
								layer.close(index);
								layer.msg("删除失败");
							}
						});
					});
				});
			}
		},page:{
			pageNumber:1,
			pageSize:6
		},
		getList : function(reload) {
			var index = layer.load();
			var search=$.trim($('[name="search"]').val());
			var order=$.trim($('[name="order"]').val());
			var type=$.trim($('[name="type"]').val());
			$("#noGoods").addClass("hidden");
			$('#typeInfo').text(" "+$('[type="'+type+'"]').text()+" ");
			$('#noResult').text(search);
			if(reload==1){
				$scope.goodsIndex.data=[];
				$scope.goodsIndex.page.pageNumber=1;
			}
			$.ajax({
				url : "/cms/goods/list",
				type : "post",
				data : {
					name:search,
					description:search,
					order:order,
					type:type,
					pageNumber:$scope.goodsIndex.page.pageNumber,
					pageSize:$scope.goodsIndex.page.pageSize
				},
				success : function(data) {
					layer.close(index);
					var list = data;
					if(list.data&&list.data.length>0){
						$scope.$apply(function() {
							var data=$scope.goodsIndex.data;
							$scope.goodsIndex.data=data.concat(list.data);
						});
					}else{
						if($scope.goodsIndex.data.length==0){
							$("#noGoods").removeClass("hidden");
						}else{
							layer.msg("到底了，所有商品都在这了...");
						}
						if($scope.goodsIndex.page.pageNumber>1){
							$scope.goodsIndex.page.pageNumber-=1;
						}
					}
				}
			});
			return false;
		},
		openApp : function(url) {
			return
			var ua = navigator.userAgent.toLowerCase();
			var tb = url.replace("http://", "").replace("https://", "");
			if (ua.match(/iphone os 9/i) == "iphone os 9") {
				window.location = "taobao://" + tb;
				window.setTimeout(function() {
					window.location = url;
				}, 4000);
			} else {
				var ifr = document.createElement('iframe');
				ifr.src = 'taobao://' + tb;
				ifr.style.display = 'none';
				document.body.appendChild(ifr);
				window.location = url;
			}
		},
	};
	$scope.goodsIndex.init();
});
