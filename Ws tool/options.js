var hotkeyList = [];
var internetUrlLocal = 'internetUrl';
if (typeof (angular) != 'undefined') {
	var app = angular.module('optionApp', [ 'ngRoute' ]).config(
			[
					'$routeProvider',
					function($routeProvider) {
						$routeProvider.when('/', {
							templateUrl : 'page/request.html',
							controller : 'optionCtrl'
						}).when('/satellite', {
							templateUrl : 'page/satellite.html',
							controller : 'satelliteCtrl'
						}).when('/map', {
							templateUrl : 'page/map.html',
							controller : 'mapCtrl'
						}).when('/request', {
							templateUrl : 'page/request.html',
							controller : 'optionCtrl'
						}).when('/keys', {
							templateUrl : 'page/keys.html',
							controller : 'keysCtrl'
						}).when('/tools', {
							templateUrl : 'page/tools.html',
							controller : 'toolsCtrl'
						}).when('/search', {
							templateUrl : 'page/search.html',
							controller : 'searchCtrl'
						}).otherwise({
							redirectTo : '/'
						});
						$("#multiSearch").on(
								"click",
								function() {
									var keyWord = $("#keyWord").val();
									if (!keyWord) {
										layer.msg("你想搜索些什么呢？");
										return;
									}
									window.location.href = window.location.href.substr(0, window.location.href
											.indexOf("#"))
											+ '#/search?keyWord=' + keyWord;
								});
						$("#multiSearchReset").on("click", function() {
							layer.closeAll();
						});
					} ]);
	app.run([ '$rootScope', '$location', function($rootScope, $location) {
		$rootScope.$on('$routeChangeSuccess', function(evt, current, previous) {
		});
	} ])
	app.directive("pluginsClick", function() {
		return {
			link : function($scope, elem, attrs) {
				$(elem).click($scope.keys[attrs.pluginsClick]);
			}
		}
	});
	app.controller('optionCtrl', function($scope, $rootScope) {
		$scope.sendHistory = [];
		var length = localStorage.length;
		for (var i = 0; i < length; i++) {
			$scope.sendHistory.push(localStorage.key(i));
		}
		$scope.request = {
			method : 'get',
			result : '',
			show : function() {
				$("#showHistory").show();
			},
			send : function(history) {
				$("#showHistory").hide();
				this.result = "";
				if (history) {
					var historyData = localStorage.getItem(history) || "{}";
					historyData = JSON.parse(historyData);
					this.url = historyData.url;
					this.data = historyData.data;
					this.method = historyData.type;
				}
				localStorage.setItem(this.url, JSON.stringify({
					url : this.url,
					data : this.data,
					method : this.method
				}));

				if ($scope.sendHistory.indexOf(this.url) == -1) {
					$scope.sendHistory.push(this.url);
				}
				if (this.url) {
					if (this.url.indexOf("?") != -1) {
						this.data = JSON.stringify(getUrlParam(this.url.substring(this.url.indexOf("?"))), null, "\t");
					}
				} else {
					layer.msg("请输入请求地址..");
					return;
				}
				layer.load(3);
				var options = {
					data : this.data,
					type : this.method,
					success : function(data) {
						$scope.request.result = JSON.stringify(data, null, "\t");
						$scope.$apply();
						layer.closeAll();
					},
					error : function(data) {
						$scope.request.result = JSON.stringify(data, null, "\t");
						$scope.$apply();
						layer.closeAll();
					}
				};
				if (this.method == 'get') {
					options.url = this.url;
					options.data = {};
				} else {
					if (this.url.indexOf("?") != -1) {
						options.url = this.url.substring(0, this.url.indexOf("?"));
					} else {
						options.url = this.url;
					}
					options.data = JSON.parse(this.data);
				}

				$.ajax(options);
			},
			handleUrl : function() {
				if (this.url) {
					if (this.url.indexOf("?") != -1) {
						// get请求
						this.data = JSON.stringify(getUrlParam(this.url.substring(this.url.indexOf("?"))), null, "\t");
						this.url = this.url.substring(0, this.url.indexOf("?"));
						this.method = 'post';
					} else {
						// post请求
						if (this.data) {
							this.url = this.url + "?" + toQueryString(JSON.parse(this.data));
							this.data = "";
						}
						this.method = 'get';
					}
				} else {
					layer.msg("请输入请求地址..");
				}
			},
			check : function() {
				if (this.url) {
					if (this.url.indexOf("?") != -1) {
						// get请求
						this.method = 'get';
					}
				}
			}
		}

	});
	app
			.controller(
					"mapCtrl",
					function($scope, $routeParams) {
						if ($routeParams && $routeParams.internetUrl) {
							$scope.internetUrl = $routeParams.internetUrl;
						}
						var height = window.screen.availHeight - 230;
						var width = window.screen.availWidth;
						$("#map").css("height", height + "px");
						$('#previewHistory').bind('mousewheel', function(event) {
							var target = null;
							if (event.originalEvent.wheelDelta < 0) {
								target = $(".previewHistory li.bg-success ").next();
							} else {
								target = $(".previewHistory li.bg-success ").prev();
							}
							if (target) {
								target.find("span.history").click();
							}
						});
						hotkeys('shift+ctrl+alt+h', function(event, handler) {
							$scope.$apply();
						});
						hotkeys('shift+ctrl+alt+s', function(event, handler) {
							if (event.target.id == "map") {
							} else {
							}
						});
						$scope.map = {
							target : null,
							renderPreviewHistory : function() {
								$scope.previewHistory = JSON.parse(localStorage.getItem(internetUrlLocal)) || [];
							},
							previewList : [
									{
										name : "城市总体规划",
										value : "http://gh.xz.gov.cn/ghj/UploadFile/c2e03efb-2f49-4070-9bc4-09db8dfb4f2a/%E5%BE%90%E5%B7%9E%E5%9F%8E%E5%B8%82%E6%80%BB%E4%BD%93%E8%A7%84%E5%88%92%E5%9B%BE.jpg"
									}, {
										name : "轨道交通线网规划",
										value : "http://www.xzgdjt.com/upload/201601/28/201601280943225830.jpg"
									}, {
										name : "轨道交通线网规划方案",
										value : "picture/metro11.jpg"
									}, {
										name : "区远景规划图",
										value : "http://www.zgts.gov.cn/upfile/Image/201007/15/17224411.jpg"
									}, {
										name : "镇规划图",
										value : "http://www.zgts.gov.cn/upfile/Image/201007/16/10470748.jpg"
									}, {
										name : "128户型西户",
										value : "picture/128户型西户.jpg"
									} ],
							init : function(url, obj) {
								if (obj) {
									$(".previewHistory li").removeClass("bg-success");
									$(obj.target).parent().addClass("bg-success");
								}
								this.renderPreviewHistory();
								var target = this.target;
								if (!url) {
									$scope.selectedUrl = $scope.map.previewList[0];
									url = $scope.internetUrl || $scope.selectedUrl.value;
								}
								if (target) {
									target.remove();
								}
								$scope.map.target = L.map('map', {
									crs : L.CRS.Simple
								});
								var target = $scope.map.target;
								var img = new Image();
								img.src = url;
								var load = function(img) {
									if (img.complete) {
										layer.closeAll();
										var bounds = [];
										// 适配高度
										bounds = [ [ 0, 0 ], [ height, img.width * (height) / img.height ] ];
										// 适配宽度
										// bounds = [ [ 0, 0 ], [ width *
										// img.height / img.width, width ] ];
										try {
											var image = L.imageOverlay(url, bounds).addTo(target);
											target.fitBounds(bounds);
										} catch (e) {
											layer.msg("请选择图片类型..");
											console.log(e)
										}
									} else {
										layer.load(1);
										setTimeout(function() {
											load(img);
										}, 1000);
									}
								}
								load(img);

							},
							reset : function() {
								$scope.internetUrl = "";
								this.renderPreviewHistory();
								$scope.nativeUrl = "";
								$("#nativeUrl").val('')
								$scope.selectedUrl = "";
							},
							removeHistory : function(history) {
								var temp = $scope.previewHistory;
								$.each($scope.previewHistory, function(i, v) {
									if (v == history) {
										temp.splice(i, 1);
									}
								});
								$scope.previewHistory = temp;
								localStorage.setItem(internetUrlLocal, JSON.stringify(temp));
								setTimeout(function() {
									$(".previewHistory li.bg-success ").find("span.history").click();
								});
							}
						}
						$scope.$on('$routeChangeSuccess', function(event, current, previous) {
							$scope.map.init();
							$("#nativeUrl").on("change", function() {
								var fr = new FileReader();
								fr.onloadend = function(e) {
									$scope.map.init(e.target.result);
								};
								fr.readAsDataURL(this.files[0]);

							});
						});
					});
	app
			.controller(
					"satelliteCtrl",
					function($scope) {
						$("#satellite,#satelliteTwo").css("height", window.screen.availHeight - 0 + "px");
						$scope.satellite = {
							oneShow : false,
							twoShow : true,
							access_token : "pk.eyJ1Ijoid2lsbGlhbjFzdCIsImEiOiJjajZwcnFvNzYwMjhrMzJxaXBuZWFnd3M4In0.20BoKHxuGi_Jw7kGgKMTcA",
							init : function() {
								var map = L.map('satellite').setView([ 34.205770, 117.283939 ], 13);
								L.tileLayer(
										'https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token='
												+ this.access_token, {
											maxZoom : 22,
											attribution : 'Imagery © <a href="http://mapbox.com">Mapbox</a>',
											id : 'mapbox.satellite'
										}).addTo(map);
								var ZoomViewer = L.Control.extend({
									onAdd : function() {
										var gauge = L.DomUtil.create('div');
										gauge.style.width = '100px';
										gauge.style.background = 'rgba(255,255,255,0.5)';
										gauge.style.textAlign = 'left';
										map.on('zoomstart zoom zoomend', function(ev) {
											gauge.innerHTML = '&nbsp;&nbsp;缩放等级：' + map.getZoom();
										})
										return gauge;
									}
								});
								(new ZoomViewer).addTo(map);
							},
							changeMap : function() {
								this.oneShow = !this.oneShow;
								this.twoShow = !this.twoShow;
							}
						}
						$scope.$on('$routeChangeSuccess', function(event, current, previous) {
							$scope.satellite.init();
						});
					});
	app.controller("keysCtrl", function($scope) {
		$scope.hotkeyActions = [ {
			key : "url",
			value : "跳转网址"
		}, {
			key : "button",
			value : "触发界面中按钮"
		}, {
			key : "custom",
			value : "自定义脚本"
		} ];
		var modal = $('#modal');
		$scope.keys = {
			itemkey : "option",
			options : [],
			init : function() {
				this.options = JSON.parse(localStorage.getItem(this.itemkey)) || [];
				hotkeyList = this.options;
				this.initCheckBoxs("#checkAll");
			},
			hotkeyAdd : function() {
				$scope.form = {};
				$scope.$apply();
				modal.modal();
			},
			hotkeySave : function() {
				if (!$scope.form || !$scope.form.hotkey) {
					layer.msg("请指定快捷键");
					return;
				}
				var object = $scope.keys.getObject($scope.form.hotkey, $scope.keys.options);
				if (object && object.hotkey == $scope.form.hotkey) {
					layer.msg("快捷键：" + object.hotkey + " 已存在");
				} else {
					$scope.keys.options.push($scope.form);
					localStorage.setItem($scope.keys.itemkey, angular.toJson($scope.keys.options));
					modal.modal('hide');
					layer.msg("快捷键：" + $scope.form.hotkey + " 保存成功");
				}
			},
			hotkeyDelete : function() {
				var hotkey = $scope.keys.getCheckBoxValues();
				if (hotkey.length == 0) {
					layer.msg("请选择一条记录");
				} else {
					layer.confirm("确定移除吗？", function() {
						$.each(hotkey, function(i, v) {
							$scope.keys.removeObject(v, $scope.keys.options);
						});
					});
				}
			},
			hotkeyEdit : function() {
				var hotkey = $scope.keys.getCheckBoxValues() || [];
				if (hotkey.length == 0) {
					layer.msg("请选择一条记录");
				} else {
					$scope.form = $scope.keys.getObject(hotkey, $scope.keys.options);
					modal.modal();
					$scope.$apply();
				}
			},
			getObject : function(key, array) {
				if (!key || !$.isArray(array)) {
					return;
				}
				var arrayIds = [];
				var object = {};
				$.each(array, function(i, v) {
					if (v.hotkey == key) {
						object = v;
					}
				});
				return object
			},
			removeObject : function(key, array) {
				if (!key || !$.isArray(array)) {
					return;
				}
				var arrayIds = [];
				var object = {};
				var temp = [];
				$.each(array, function(i, v) {
					if (v.hotkey != key) {
						temp.push(v);
					}
				});
				if (temp.length < array.length) {
					layer.msg("删除快捷键：" + key + " 成功");
					$scope.keys.options = temp;
					localStorage.setItem($scope.keys.itemkey, angular.toJson($scope.keys.options));
				} else {
					layer.msg("删除快捷键：" + key + " 已经不存在");
				}
				$scope.$apply();
			},
			/**
			 * selector：选择符，必填
			 */
			initCheckBoxs : function(selector) {
				if (selector) {
					// 全选按钮
					var selectObj = $("table " + selector);
					selectObj.change(function() {
						var checked = $(this).is(":checked");
						var checkBoxList = $("table tbody td input[type='checkbox']");
						if (checked) {
							checkBoxList.attr("checked", "checked")
						} else {
							checkBoxList.removeAttr("checked");
						}
					});
				} else {
					if (console) {
						console.error("请传入选择符！")
					}
				}
			},
			/**
			 * 获取复选框的值
			 */
			getCheckBoxValues : function() {
				var ids = [];
				// 列中的选择框
				var checkBoxList = $("table tbody td input[type='checkbox']");
				checkBoxList.each(function() {
					var obj = $(this);
					if (obj.is(":checked")) {
						ids.push(obj.attr("dataid"));
					}
				});
				return ids;
			}
		}
		$scope.$on('$routeChangeSuccess', function(event, current, previous) {
			$scope.keys.init();
		});
	});
	app.controller("toolsCtrl", function($scope) {
		$scope.tools = {
			init : function() {
				$("#fileToPlay").on("change", function() {
					$scope.tools.play();
				});
				var files = localStorage.getItem("playList");
				if (files) {
					$scope.tools.files = JSON.parse(files);
				}
				$("#addressToPlay").on("change", function() {
					$scope.tools.play(this.value);
				});
			},
			play : function(data) {
				var player = document.getElementById('PlayFlie');
				if (!data) {
					$scope.tools.files = document.getElementById('fileToPlay').files;
					$.each($scope.tools.files, function(i, v) {
						var url = URL.createObjectURL($scope.tools.files[i]);
						this.url = url;
						this.filename = $scope.tools.files[i].name;
					});
					localStorage.setItem("playList", JSON.stringify($scope.tools.files));
					$("#PlayFlie").attr("src", $scope.tools.files[0].url);
					$scope.$apply();
				} else {
					$("#PlayFlie").attr("src", data);
					player.play();
				}

			},
			files : []
		}
		$scope.$on('$routeChangeSuccess', function(event, current, previous) {
			$scope.tools.init();
		});
	});
	app.controller("searchCtrl", function($scope, $http, $rootScope, $routeParams) {
		$scope.tools = {
			init : function() {
				var switchFlag = false;
				var url = [ {
					web : "http://www.baidu.com/s?wd=" + $routeParams.keyWord,
					mobile : "http://m.baidu.com/s?word=" + $routeParams.keyWord
				}, {
					web : "http://www.so.com/s?q=" + $routeParams.keyWord,
					mobile : "https://m.so.com/s?q=" + $routeParams.keyWord
				}, {
					web : "http://www.sogou.com/web?query=" + $routeParams.keyWord,
					mobile : "https://m.sogou.com/web/searchList.jsp?keyword=" + $routeParams.keyWord
				}, {
					web : "https://cn.bing.com/search?q=" + $routeParams.keyWord,
					mobile : "https://cn.bing.com/search?q=" + $routeParams.keyWord
				} ];
				$(".search-result").css("height", $(window).height() - 100);
				$.each(url, function(i, v) {
					$("#result" + i).attr("src", v.mobile);
				});
				return;
				$("#result1").attr("src", "http://www.baidu.com/s?wd=" + $routeParams.keyWord);
				$("#result2").attr("src", "http://www.so.com/s?q=" + $routeParams.keyWord);
				$("#result3").attr("src", "http://www.sogou.com/web?query=" + $routeParams.keyWord);
				$("#result4").attr("src", "https://cn.bing.com/search?q=" + $routeParams.keyWord);
			}
		}
		$scope.$on('$routeChangeSuccess', function(event, current, previous) {
			$scope.tools.init();
		});
	});
}
// 给content_script(前端)发送数据
sendToFront = function() {
	chrome.tabs.query({
		active : true,
		currentWindow : true
	}, function(tabs) {
		chrome.tabs.sendMessage(tabs[0].id, {
			message : "calculate"
		}, function(response) {
			if (typeof response != 'undefined') {
				alert(response);
			} else {
			}
		});
	});
}
// 对象是否在数组中
getObject = function(key, array) {
	if (!key || !$.isArray(array)) {
		return;
	}
	var arrayIds = [];
	var object = {};
	$.each(array, function(i, v) {
		if (v.hotkey == key) {
			object = v;
		}
	});
	return object
}
// 获取url中的参数并转换为对象
function getUrlParam(url) {
	var thisParam = new Object();
	// 判断是否存在请求的参数
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		// 截取所有请求的参数，以数组方式保存
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			// 获取该参数名称，值。其中值以unescape()方法解码，有些参数会加密
			thisParam[strs[i].split("=")[0]] = decodeURIComponent(strs[i].split("=")[1]);
		}
	}
	// 返回改参数列表对象
	return thisParam;
}
// 将对象转换为url中的参数
function toQueryPair(key, value) {
	if (typeof value == 'undefined') {
		return key;
	}
	return key + '=' + (value === null ? '' : String(value));
}
function toQueryString(obj) {
	var ret = [];
	for ( var key in obj) {
		// key = encodeURIComponent(key);
		var values = obj[key];
		if (values && values.constructor == Array) { // 数组
			var queryValues = [];
			for (var i = 0, len = values.length, value; i < len; i++) {
				value = values[i];
				queryValues.push(toQueryPair(key, value));
			}
			ret = ret.concat(queryValues);
		} else { // 字符串
			ret.push(toQueryPair(key, values));
		}
	}
	return ret.join('&');
}
// 给background和popup发送数据
if (chrome.extension) {
	chrome.extension.onRequest.addListener(function(request, sender, sendResponse) {
		var hotkey = request.cmd.replace(/^\"|\"$/g, '');
		var object = getObject(hotkey, hotkeyList);
		if (object && object.hotkey == hotkey) {
			// console.log("快捷键：" + object.hotkey + " 已存在");
		} else {
			hotkeyList.push({
				hotkey : hotkey
			});
			localStorage.setItem('option', angular.toJson(hotkeyList));
			// console.log("快捷键：" + hotkey + " 保存成功");
		}

		if (request.cmd == internetUrlLocal) {
			var internetUrls = localStorage.getItem("internetUrl");
			internetUrls = JSON.parse(internetUrls) || [];
			var flag = false;
			$.each(internetUrls, function(i, v) {
				if (v == request.internetUrl) {
					flag = true;
				}
			})
			if (flag) {
				console.log("URL已存在");
			} else {
				internetUrls.push(request.internetUrl);
				localStorage.setItem(internetUrlLocal, JSON.stringify(internetUrls));
				console.log("URL保存成功");
			}
			location.href = "#/map?internetUrl=" + request.internetUrl;

		}
	});
}
