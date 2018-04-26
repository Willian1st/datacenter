var cacheKeyWord = {
	key : "",
	station_key : "",
	line_key : "",
	start_key : "",
	start_lonlat : "",
	end_key : "",
	timeStamp : ""
};
// 初始化滚动高度
var height = $(window).height();
// 初始化这常用站台
localStorage.setItem("storedLines", [ "27", "115", "118", "126", "146", "168",
		"176", "177", "180", "207", "快线2号" ])
var storedLines = localStorage.getItem("storedLines");
$(function() {
	jQuery("#searchLine").on('click', function() {
		var lineNumber = jQuery("#busLine").val();
		if ($.trim(lineNumber) == '') {
			alert("请输入检索内容...");
			return;
		}
		getBusInfoByLineNumber(lineNumber);
	});
	/**
	 * 自动补全
	 */
	$("#busLine").on("keyup focus", function() {
		var text = $(this).val();
		var buslines = storedLines.split(',');
		$("#auto li:gt(0)").remove();
		$.each(buslines, function(i, e) {
			if (e.indexOf(text) != -1) {
				var li = $("#auto li:first").clone();
				li.removeClass("invisible");
				$("#auto").append(li.html(e));
			}
		});
	});
	// 动态绑定单击事件
	$("#auto").on("click", "li", function() {
		var lineNumber = $(this).text();
		jQuery("#busLine").val(lineNumber);
		getBusInfoByLineNumber(lineNumber);
		$("#auto li:gt(0)").remove();
	});
	// $(document).on("scrollstart", function() {
	// height = 0;
	// });
	/**
	 * 滚动结束时对height赋值
	 */
	$(document).on("scrollstop", function() {
		height = $(document).scrollTop();
	});
	// $(document).on("scroll", function() {
	// alert($(document).scrollTop());
	// })
	/**
	 * 屏幕旋转时触发
	 */
	$(window).on("orientationchange", function() {
		if (window.orientation == 0) // Portrait
		{
		} else // Landscape
		{
		}
	});
})

/**
 * 根据站台名搜索线路
 */
function orignal() {
	$
			.ajax({
				url : "http://api.sz-map.com/sip/tf?m=gbsbn&s=a&f=json&type=TOUCH&stationName="
						+ encodeURIComponent(encodeURIComponent(118))
						+ "&t="
						+ this.timeStamp,
				async : !0,
				dataType : "jsonp",
				timeout : 3e4,
				beforeSend : function() {
					showLoading()
				},
				complete : function() {
					hideLoading()
				},
				error : function() {
					alert('请求超时')
				},
				success : function(t) {
					var jsonStr = JSON.stringify(t);
					alert(jsonStr);
				}
			});
}
/**
 * 根据线路搜索公交线
 */
function getBusInfoByLineNumber(lineNumber) {
	var busKey = '';
	$
			.ajax({
				url : "http://api.sz-map.com/sip/tf?m=gblbn&s=a&f=json&type=TOUCH&name="
						+ encodeURIComponent(encodeURIComponent(lineNumber))
						+ "&t=" + '',
				async : !0,
				dataType : "jsonp",
				timeout : 3e4,
				beforeSend : function() {
					showLoading()
				},
				complete : function() {
					hideLoading()
				},
				error : function() {
					alert('请求超时')
				},
				success : function(t) {
					var jsonStr = JSON.stringify(t);
					// "totleSize":2,"errorCode":"","lines":[{"guid":"435a559b-c452-4b9e-8d89-49527a4df2c7","stime":"5:00-19:00","count":"36站","etime":"6:00-20:00","PID":"402882b82d4e97a4012d4e99aae34544","direction":"车坊首末站","description":"葑门首末站=>车坊首末站","name":"118路","compguid":"4d95ac5d-d54c-4e15-9c5d-aff153d77c30"},
					// {"guid":"f7da65db-b859-4213-be9e-713c2d5051a1","stime":"6:00-20:00","count":"36站","etime":"5:00-19:00","PID":"402882b82d4e97a4012d4e99dcff6432","direction":"葑门首末站","description":"车坊首末站=>葑门首末站","name":"118路","compguid":"4d95ac5d-d54c-4e15-9c5d-aff153d77c30"}],"success":true
					if (t.success) {
						$
								.each(
										t.lines,
										function(i) {
											var ul = $("#lineResultModel ul")
													.clone();
											var button = $("#lineResultModel a")
													.clone();
											busKey = t.lines[i].guid;
											// show line result
											ul.find("#name").text(
													t.lines[i].name);
											ul.find("#direction").text(
													t.lines[i].direction);
											ul.find("#description").text(
													t.lines[i].description);
											ul.find("#stime").text(
													t.lines[i].stime);
											ul.find("#etime").text(
													t.lines[i].etime);
											ul.find("#count").text(
													t.lines[i].count);
											ul.find("#bus_id").text(busKey);
											if (i == 0) {
												$("#lineResult .ui-block-a")
														.html(ul);
												button
														.insertBefore($("#lineResult .ui-block-a ul"));
											} else if (i == 1) {
												$("#lineResult .ui-block-b")
														.html(ul);
												button
														.insertBefore($("#lineResult .ui-block-b ul"))
											} else if (i == 3) {
												$("#lineResult .ui-block-c")
														.html(ul);
												button
														.insertBefore($("#lineResult .ui-block-c ul"))
											} else if (i == 4) {
												$("#lineResult .ui-block-d")
														.html(ul);
												button
														.insertBefore($("#lineResult .ui-block-d ul"))
											}
										})
					} else {
						alert("路线不存在...")
					}

				}
			})
}
var interval;
/**
 * 根据路线显示公交站台
 */
function findByBusKey(temp) {
	height = 0;
	var busKey = $(temp).parent().find("#bus_id").text();
	$("#bus_id_in_result").text(busKey);
	// location.href = "station_result.html?bus_id=" + busKey + "'";
	getLineDetail(busKey);
	//每十秒刷新一次
	interval = setInterval(getLineDetail(busKey), "10000");
}
function getLineDetail(busKey) {
	var t = "http://api.sz-map.com/sip/r?m=searchStation&s=a&type=TOUCH&dataGuid="
			+ busKey + "&f=json&t=" + '';
	$.ajax({
		url : t,
		async : !0,
		dataType : "jsonp",
		timeout : 3e4,
		beforeSend : function() {
			showLoading()
		},
		complete : function() {
			hideLoading()
		},
		error : function() {
			alert('请求超时')
		},
		success : function(t) {
			jQuery("#LDirection").text(t.LDirection);
			jQuery("#FETime").text(t.FETime);
			jQuery("#lName").text(t.lName);
			var stations = t.stations;
			$("#station_list li:gt(5)").remove();
			$.each(stations, function(i) {
				var station = $("#station_list li:eq(1)").clone();
				station.find("#SName").text(stations[i].SName);
				if (stations[i].BusInfo) {
					station.addClass("location");
					station.find("#InTime").text(stations[i].InTime);
					station.find("#BusInfo").text("  " + stations[i].BusInfo);
				}
				station.removeClass("invisible");
				$("#station_list").append(station);
			});
			var jsonStr = JSON.stringify(t);
		}
	});
}
/**
 * 点击"刷新",重新获取站台信息
 */
function refreshLineDetail() {
	getLineDetail($("#bus_id_in_result").text());
}
/**
 * 点击"向下"时设置滚动高度
 */
function setHeight() {
	height += $(window).height()
	$("html,body").animate({
		"scrollTop" : height
	}, 500);
}
/**
 * 点击"顶部"时,滚动到最顶部
 */
function toTop() {
	height = 0;
	$("html,body").animate({
		"scrollTop" : 0
	}, 500);
}
/**
 * 显示加载动画
 */
function showLoading() {
	$(".loading").css("z-index", 999);
}
/**
 * 隐藏加载动画
 */
function hideLoading() {
	$(".loading").css("z-index", -1);
}

/**
 * 测试alert
 */
function testAlert() {
	alert("测试Alert事件");
}
/**
 * 采用正则表达式获取地址栏参数
 */
function getParamFromAddress(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]).replace(/'/g, "");
	return null;
}