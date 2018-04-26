var timeStamp = $("#tm").attr("value"),
transferType = "MinTransfer"; !
function(t) {
	t.iScroll = function() {
		setTimeout(function() {
			document.ontouchmove = function(t) {
				return t.preventDefault(),
				!1
			},
			myScroll = new iScroll(document.getElementById("scroller"))
		},
		100)
	}
} ($),
function(t) {
	t.decodeLonLat = function(t) {
		for (var e = t.length,
		i = 0,
		n = [], a = 0, o = 0; e > i;) {
			var s, r = 0,
			c = 0;
			do s = t.charCodeAt(i++) - 63,
			c |= (31 & s) << r,
			r += 5;
			while (s >= 32);
			var l = 1 & c ? ~ (c >> 1) : c >> 1;
			a += l,
			r = 0,
			c = 0;
			do s = t.charCodeAt(i++) - 63,
			c |= (31 & s) << r,
			r += 5;
			while (s >= 32);
			var d = 1 & c ? ~ (c >> 1) : c >> 1;
			o += d,
			n.push({
				x: 1e-6 * a,
				y: 1e-6 * o
			})
		}
		return n
	}
} ($),
function(t) {
	t.encodeLonLat = function(t) {
		t instanceof Array || (t = [t]);
		for (var e = function(t) {
			var e = t << 1;
			0 > t && (e = ~e);
			for (var i = ""; e >= 32;) i += String.fromCharCode((32 | 31 & e) + 63),
			e >>= 5;
			return i += String.fromCharCode(e + 63)
		},
		i = 0, n = 0, a = "", o = 0; o < t.length; ++o) {
			var s = t[o],
			r = Math.round(1e6 * s.y),
			c = Math.round(1e6 * s.x),
			l = r - i,
			d = c - n;
			i = r,
			n = c,
			a += e(d) + e(l)
		}
		return a
	}
} ($),
function(t) {
	t.requestTM = function(e, i) {
		t.ajax({
			url: "tm.php",
			async: !0,
			dataType: "json",
			timeout: 3e4,
			error: function() {},
			success: function(t) {
				timeStamp = t,
				i.call(e)
			}
		})
	}
} ($);
var cacheKeyWord = {
	station_key: "",
	line_key: "",
	start_key: "",
	start_lonlat: "",
	end_key: ""
};
BusTemplate = ['<div class="content" style="bottom:60px;top:0;">', "<header>", '<div class="logo"><a href="http://wap.139sz.cn/jsp/newIndex/index.jsp" class="button back_home">返回掌苏</a></div>', '<div class="title">实时公交</div>', "</header>", '<div class="station_content">', '<div class="s_tips">查站点</div>', "<div>", '<form id="station_form">', '<input id="station_key" type="text" placeholder="输入您要查询的站点">', '<a class="button-positive s_btn" >查询</a>', "</form>", "</div>", '<div class="hot_station">', "<a>三元坊</a>", "<a>观前街</a>", "<a>火车站</a>", "</div>", "</div>", '<div class="line_content">', '<div class="l_tips">查线路</div>', "<div>", '<form id="line_form">', '<input id="line_key" type="text" placeholder="输入您要查询的线路">', '<a class="button-positive l_btn">查询</a>', "</form>", "</div>", '<div class="hot_line">', "<a>游4</a>", "<a>快线6号</a>", "<a>1路</a>", "<a>923路</a>", "</div>", "</div>", '<div class="version">', '<a class="apk" href="http://wap.139sz.cn/g3club/jtem/chux.html"><img src="images/icon/apk_icon.png" style="vertical-align:top;">下载139客户端，体验更棒的查询</a>', '<div><a class="cb" href="http://wap.139sz.cn/bus/">彩版</a>|<span>触屏版</span></div>', "</div>", "</div>"].join("\n");
var BusView = Jr.View.extend({
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[0]).addClass("active"),
		this.$el.html(BusTemplate),
		this.addHotLink(),
		document.body.scrollTop = 0,
		setTimeout(function() {
			var t = /android/gi.test(navigator.appVersion);
			t && $("a.apk").css("display", "block"),
			$("#ajax_loading").css("display", "none"),
			$("#map_tips").css("opacity", 0),
			"" != cacheKeyWord.station_key && this.$("#station_key").attr("value", cacheKeyWord.station_key),
			"" != cacheKeyWord.line_key && this.$("#line_key").attr("value", cacheKeyWord.line_key),
			this.$("#station_form").on("submit", this.onSearchStation.bind(this)),
			this.$("#line_form").on("submit", this.onSearchLine.bind(this))
		}.bind(this), 100),
		this
	},
	events: {
		"click .s_btn": "onSearchStation",
		"click .l_btn": "onSearchLine"
	},
	onSearchStation: function(t) {
		t.preventDefault();
		var e = this.$("#station_key").attr("value");
		"" != e.trim() && (cacheKeyWord.station_key = e, e = encodeURIComponent(encodeURIComponent(e)), Jr.Navigator.navigate("#/s/" + e, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		}))
	},
	onSearchLine: function(t) {
		t.preventDefault();
		var e = this.$("#line_key").attr("value");
		"" != e.trim() && (cacheKeyWord.line_key = e, e = encodeURIComponent(encodeURIComponent(e)), Jr.Navigator.navigate("#/l/" + e, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		}))
	},
	addHotLink: function() {
		var t = this.$("div.hot_station a");
		t.on("click",
		function() {
			var t = $(this).html();
			Jr.Navigator.navigate("#/s/" + encodeURIComponent(encodeURIComponent(t)), {
				trigger: !0,
				animation: {
					type: Jr.Navigator.animations.SLIDE_STACK,
					direction: Jr.Navigator.directions.LEFT
				}
			})
		});
		var e = this.$("div.hot_line a");
		e.on("click",
		function() {
			var t = $(this).html();
			Jr.Navigator.navigate("#/l/" + encodeURIComponent(encodeURIComponent(t)), {
				trigger: !0,
				animation: {
					type: Jr.Navigator.animations.SLIDE_STACK,
					direction: Jr.Navigator.directions.LEFT
				}
			})
		})
	}
});
SearchStationTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_margin" >返回</a>', '<h1 class="title">查询站点</h1>', "</header>", '<div class="bar-standard bar-tools">', "</div>", '<div class="content content-position">', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var SearchStationView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.hash = hash,
		this.station_key = hash.substring(hash.lastIndexOf("/") + 1),
		this.station_key = decodeURIComponent(decodeURIComponent(this.station_key))
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[0]).addClass("active"),
		this.$el.html(SearchStationTemplate),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block"),
		$.ajax({
			url: "http://api.sz-map.com/sip/tf?m=gbsbn&s=a&f=json&type=TOUCH&stationName=" + encodeURIComponent(encodeURIComponent(this.station_key)) + "&t=" + timeStamp,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.stands,
		i = this.$("div.bar-tools");
		i.html('当前查询"' + this.station_key + '"').css("display", "block");
		var n = '<ul class="list customizeList_station">';
		$.each(e,
		function(t, e) {
			var i = e.address,
			a = e.guid,
			o = e.name,
			s = e.scode;
			n += '<li><a class="more_line" name=' + o + " scode=" + s + " guid=" + a + ">" + "<strong>" + o + "</strong>" + "<p>" + i + "</p>" + '<span class="chevron"></span>' + "</li>"
		}.bind(this)),
		n += "</ul>",
		this.$("div.search_result").html(n),
		this.$(".more_line").on("click", this.showNextView),
		$.iScroll()
	},
	events: {
		"click .button-positive": "onButtonPrev"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#", {
			trigger: !0
		}) : window.history.back()
	},
	showNextView: function() {
		station_guid = $(this).attr("guid"),
		station_scode = $(this).attr("scode"),
		station_name = $(this).attr("name"),
		Jr.Navigator.navigate("#/sl/" + encodeURIComponent(station_name) + "&" + station_scode + "&" + station_guid, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		})
	}
});
StationLineTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_back" >返回</a>', '<h1 class="title"></h1>', '<a class="button-positive btn_refresh" style="top:5px;">刷新</a>', "</header>", '<div class="bar-standard bar-tools">', "</div>", '<div class="content content-position" >', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var StationLineView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.hash = hash,
		this.key = hash.substring(hash.lastIndexOf("/") + 1),
		this.top = 0
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[0]).addClass("active"),
		this.$el.html(StationLineTemplate),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block");
		var t = this.key.split("&"),
		e = decodeURIComponent(t[0]),
		i = t[1],
		n = t[2];
		this.$("h1.title").html(e),
		$.ajax({
			url: "http://api.sz-map.com/sip/r?m=searchLine&s=a&type=TOUCH&dataGuid=" + i + "&lGuid=&sGuid=" + n + "&f=json&t=" + timeStamp,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.line,
		i = t.standAddr,
		n = this.$("div.bar-tools");
		n.html(i).css("display", "block");
		var a = '<ul  class="list customizeList"></li>';
		$.each(e,
		function(t, e) {
			var i = e.distince,
			n = e.guid,
			o = e.lName;
			a += "<li>",
			a += '<a class="more" lguid=' + n + ' ><div class="line_name">' + o + ( - 1 == o.indexOf("号") ? "路": "") + "</div>",
			a += '<span class="chevron"></span>',
			a += "-1" == i ? '<div class="line_distance">下趟尚未发车</div>': '<div class="line_distance">下趟车距离<font class="distance_num" > ' + i + " </font>站</div>",
			a += "</a>",
			a += "</li>"
		}),
		a += "</ul>",
		this.$("div.search_result").html(a),
		this.$(".more").on("click", this.showLineView),
		$.iScroll()
	},
	events: {
		"click .btn_back": "onButtonPrev",
		"click .btn_refresh": "onRefresh"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#", {
			trigger: !0
		}) : window.history.back()
	},
	onRefresh: function() {
		this.afterRender()
	},
	showLineView: function() {
		lguid = $(this).attr("lguid"),
		Jr.Navigator.navigate("#/lid/" + lguid, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		})
	}
});
LineStationTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_back" >返回</a>', '<h1 class="title"></h1>', '<a class="button-positive btn_refresh" >刷新</a>', '<a class="button-positive btn_return" >返程</a>', "</header>", '<div class="bar-standard bar-tools">', "</div>", '<div class="content content-position">', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var LineStationView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.key = hash.substring(hash.lastIndexOf("/") + 1),
		this.return_flag = "0"
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[0]).addClass("active"),
		this.$el.html(LineStationTemplate),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block");
		var t = "http://api.sz-map.com/sip/r?m=searchStation&s=a&type=TOUCH&dataGuid=" + this.key + "&f=json&t=" + timeStamp;
		if ("1" == this.return_flag) var t = "http://api.sz-map.com/sip/r?m=srl&s=a&type=TOUCH&dataGuid=" + this.key + "&f=json&t=" + timeStamp;
		$.ajax({
			url: t,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.FETime;
		t.LDirection;
		var i = t.lName,
		n = t.lGuid,
		a = t.stations;
		this.key = n,
		this.$("h1.title").html(i);
		var o = this.$("div.bar-tools");
		o.html("首末班：" + e).css("display", "block");
		var s = '<ul class="list customizeList"></li>',
		r = a.length;
		$.each(a,
		function(t, e) {
			e.BusInfo;
			var i = e.InTime,
			n = e.SCode,
			a = e.SGuid,
			o = e.SName;
			s += "<li>",
			s += '<a class="more ' + (null == i ? "": "station_active") + '" guid=' + a + " scode=" + n + " name=" + o + " >",
			s += '<span class="chevron" ></span>',
			s += 0 == t && null != i ? '<div class="status status_top_orange"></div>': 0 == t && null == i ? '<div class="status status_top_blue"></div>': t > 0 && r - 1 > t && null != i ? '<div class="status status_middle_orange"></div>': t > 0 && r - 1 > t && null == i ? '<div class="status status_middle_blue"></div>': t == r - 1 && null != i ? '<div class="status status_bottom_orange"></div>': t == r - 1 && null == i ? '<div class="status status_bottom_blue"></div>': '<div class="status"></div>',
			null != i && (s += '<div class="inTime">进站时间：' + i + "</div>"),
			s += null != i && o.length > 8 ? '<div class="station_name">' + o.substring(0, 8) + "...</div>": '<div class="station_name">' + o + "</div>",
			s += "</a>",
			s += "</li>"
		}),
		s += "</ul>",
		this.$("div.search_result").html(s),
		this.$(".more").on("click", this.showStationView),
		$.iScroll()
	},
	showStationView: function() {
		station_guid = $(this).attr("guid"),
		station_scode = $(this).attr("scode"),
		station_name = $(this).attr("name"),
		Jr.Navigator.navigate("#/sl/" + encodeURIComponent(station_name) + "&" + station_scode + "&" + station_guid, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		})
	},
	events: {
		"click .btn_back": "onButtonPrev",
		"click .btn_refresh": "onRefresh",
		"click .btn_return": "onReturn"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#", {
			trigger: !0
		}) : window.history.back()
	},
	onRefresh: function() {
		this.return_flag = "0",
		this.afterRender()
	},
	onReturn: function() {
		this.return_flag = "1",
		this.afterRender()
	}
});
SearchLineTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_margin" >返回</a>', '<h1 class="title">查询线路</h1>', "</header>", '<div class="bar-standard bar-tools">', "</div>", '<div class="content content-position">', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var SearchLineView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.line_key = hash.substring(hash.lastIndexOf("/") + 1),
		this.line_key = decodeURIComponent(decodeURIComponent(this.line_key))
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[0]).addClass("active"),
		this.$el.html(SearchLineTemplate),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		document.body.scrollTop = 0,
		this
	},
	events: {
		"click .button-positive": "onButtonPrev"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#", {
			trigger: !0
		}) : window.history.back()
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block"),
		$.ajax({
			url: "http://api.sz-map.com/sip/tf?m=gblbn&s=a&f=json&type=TOUCH&name=" + encodeURIComponent(encodeURIComponent(this.line_key)) + "&t=" + timeStamp,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.lines,
		i = this.$("div.bar-tools");
		i.html('当前查询&nbsp;&nbsp;"' + this.line_key + '"').css("display", "block");
		var n = '<ul class="list customizeList_station">';
		$.each(e,
		function(t, e) {
			var i = e.guid,
			a = e.name,
			o = e.direction;
			n += '<li><a class="more_line"  guid=' + i + ">" + "<strong>" + a + "</strong>" + "<p>终点站：" + o + "</p>" + '<span class="chevron"></span>' + "</li>"
		}.bind(this)),
		n += "</ul>",
		this.$("div.search_result").html(n),
		this.$(".more_line").on("click", this.showNextView),
		$.iScroll()
	},
	showNextView: function() {
		line_guid = $(this).attr("guid"),
		Jr.Navigator.navigate("#/lid/" + line_guid, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		})
	}
});
TrafficTemplate = ['<div class="content" style="bottom:60px;top:0px;">', "<header>", '<div class="logo"><a href="http://wap.139sz.cn/jsp/newIndex/index.jsp" class="button back_home">返回掌苏</a></div>', '<div class="title">公交换乘</div>', "</header>", '<form id="start_from">', '<div class="start_content">', '<div class="start_tips">出发地</div>', "<div>", '<input id="s_key" type="text" value=""  placeholder="输入出发地">', "</div>", "</div>", "</form>", '<form id="end_from">', '<div class="end_content">', '<div class="end_tips">目的地</div>', "<div>", '<input id="e_key" value="" type="text" placeholder="输入目的地">', "</div>", "</div>", "</form>", '<div style="padding: 0 20px;margin-bottom:20px;margin-top:15px;text-align:right;">', '<a class="button-positive s_btn">换乘查询</a>', "</div>", '<div class="version">', '<a class="apk" href="http://wap.139sz.cn/g3club/jtem/chux.html"><img src="images/icon/apk_icon.png" style="vertical-align:top;">下载139客户端，体验更棒的查询</a>', '<div><a class="cb" href="http://wap.139sz.cn/bus/">彩版</a>|<span>触屏版</span></div>', "</div>", "</div>"].join("\n");
var TrafficView = Jr.View.extend({
	initialize: function() {},
	init: function() {
		"" != cacheKeyWord.start_key ? (this.$("#s_key").attr("value", cacheKeyWord.start_key), this.$("#e_key").attr("value", cacheKeyWord.end_key), this.encodeLonlat = cacheKeyWord.start_lonlat) : (this.encodeLonlat = "", navigator.geolocation && navigator.geolocation.getCurrentPosition(this.success.bind(this), this.error.bind(this)))
	},
	success: function(t) {
		x = t.coords.longitude,
		y = t.coords.latitude,
		this.encodeLonlat = $.encodeLonLat({
			x: x,
			y: y
		}),
		this.$("#s_key").attr("value", "我的位置"),
		this.$("#s_key").attr("placeholder", "")
	},
	error: function() {
		this.encodeLonlat = "",
		this.$("#s_key").attr("value", "")
	},
	render: function() {
		return $("#ajax_loading").css("display", "none"),
		$("li.tab-item").removeClass("active"),
		$($("li.tab-item")[1]).addClass("active"),
		this.$el.html(TrafficTemplate),
		this.onKeyBlur(),
		setTimeout(function() {
			var t = /android/gi.test(navigator.appVersion);
			t && $("a.apk").css("display", "block"),
			$("#ajax_loading").css("display", "none"),
			$("#map_tips").css("opacity", 0),
			this.$("#start_from").on("submit", this.onSearch.bind(this)),
			this.$("#end_from").on("submit", this.onSearch.bind(this))
		}.bind(this), 100),
		setTimeout(this.init.bind(this), 100),
		this
	},
	events: {
		"click .s_btn": "onSearch",
		"click #s_key": "onKeyFocus"
	},
	onSearch: function(t) {
		t.preventDefault();
		var e = this.$("#s_key").attr("value").trim(),
		i = this.$("#e_key").attr("value").trim();
		if ("" == e) return this.$("#s_key").focus(),
		void 0;
		if ("" == i) return this.$("#e_key").focus(),
		void 0;
		cacheKeyWord.start_key = e,
		cacheKeyWord.end_key = i;
		var n = "#traffic/confirm/" + encodeURIComponent(e) + "&" + encodeURIComponent(i);
		if ("我的位置" == e && "" != this.encodeLonlat) {
			var n = "#traffic/confirm/" + encodeURIComponent(e) + "&" + encodeURIComponent(i) + "&" + this.encodeLonlat;
			cacheKeyWord.start_lonlat = this.encodeLonlat
		}
		Jr.Navigator.navigate(n, {
			trigger: !0,
			animation: {
				type: Jr.Navigator.animations.SLIDE_STACK,
				direction: Jr.Navigator.directions.LEFT
			}
		})
	},
	onKeyFocus: function() {
		var t = this.$("#s_key").attr("value").trim();
		"我的位置" == t && (document.getElementById("s_key").value = "")
	},
	onKeyBlur: function() {
		this.$("#s_key").on("blur",
		function() {
			var t = this.$("#s_key").attr("value").trim();
			"" == t && (document.getElementById("s_key").value = "我的位置")
		}.bind(this))
	}
});
ConfirmStartEndTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_margin" >返回</a>', '<h1 class="title">确认起终点</h1>', "</header>", '<div class="bar-standard bar-tools-traffic">', "</div>", '<div class="content content-position-traffic" >', '<div class="search_result" id="scroller">', "</div>", "</div>", '<div class="c_pagination"><a class="page_prev">上页</a> <a class="page_next">下页</a>  <span class="page_des">第/页</span> <span class="page_total">共个</span></div>'].join("\n");
var ConfirmStartEndView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.key = hash.substring(hash.lastIndexOf("/") + 1);
		var t = this.key.split("&");
		this.start_key = decodeURIComponent(t[0]),
		this.end_key = decodeURIComponent(t[1]),
		this.startEndFlag = "start",
		this.currentPage = 0,
		this.totalPages = 0,
		this.pageSize = 10,
		this.start_id = "",
		this.end_id = "",
		this.start_name = "",
		this.end_name = "",
		this.start_type = "pid",
		3 == t.length && "" != t[2].trim() && (this.start_id = t[2], this.start_name = this.start_key, this.startEndFlag = "end", this.start_type = "ll")
	},
	render: function() {
		$("li.tab-item").removeClass("active"),
		$($("li.tab-item")[1]).addClass("active"),
		this.$el.html(ConfirmStartEndTemplate);
		var t = this.$("div.bar-tools-traffic");
		return t.html('<div class="confirm_start"><font color="green">起点：</font>' + this.start_key + '<font color="red">&nbsp;&nbsp;(待确定)</font></div>' + '<div class="confirm_end"><font color="green">终点：</font>' + this.end_key + '<font color="red">&nbsp;&nbsp;(待确定)</font></div>').css("display", "block"),
		"ll" == this.start_type && this.$("div.confirm_start").html('<font color="green">起点：</font>' + this.start_key),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		document.body.scrollTop = 0,
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block");
		var t = "";
		t = "start" == this.startEndFlag ? this.start_key: this.end_key,
		$.ajax({
			url: "http://api.sz-map.com/sip/p?m=spbk&s=a&f=json&type=TOUCH&poiType=n4100303&k=" + encodeURIComponent(encodeURIComponent(t)) + "&t=" + timeStamp + "&i=" + this.currentPage * 10 + "&l=" + this.pageSize,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning_traffic")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning_traffic")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.pois.poi,
		i = t.pois.count;
		this.totalPages = Math.ceil(i / this.pageSize),
		this.totalPages <= 1 && (this.$("a.page_prev").css("display", "none"), this.$("a.page_next").css("display", "none")),
		0 == this.currentPage && this.$("a.page_prev").css("display", "none"),
		this.currentPage > 0 && this.$("a.page_prev").css("display", "inline"),
		this.currentPage == this.totalPages - 1 && this.$("a.page_next").css("display", "none"),
		this.currentPage < this.totalPages - 1 && this.$("a.page_next").css("display", "inline"),
		this.$("div.c_pagination").css("display", "block"),
		this.$("span.page_des").html("第" + (this.currentPage + 1) + "/" + this.totalPages + "页"),
		this.$("span.page_total").html("共" + i + "个");
		var n = '<ul class="list customizeList_station"><li class="list-divider">请选择准确的' + ("start" == this.startEndFlag ? "起点": "终点") + "</li>";
		$.each(e,
		function(t, e) {
			var i = e.name,
			a = e.address,
			o = e.id;
			n += '<li><a class="traffic_poi" id=' + o + " name=" + i + ">" + "<strong>" + i + "</strong>" + "<p>" + a + "</p>" + "</li>"
		}),
		n += "</ul>",
		this.$("div.search_result").html(n),
		"start" == this.startEndFlag ? $.each(this.$(".traffic_poi"),
		function(t, e) {
			$(e).on("click",
			function() {
				"start" == this.startEndFlag && (this.start_id = $(e).attr("id"), this.start_name = $(e).attr("name"), this.$("div.confirm_start").html('<font color="green">起点：</font>' + this.start_name), this.startEndFlag = "end", this.initPagination(), this.$("div.search_result").html(""), this.afterRender())
			}.bind(this))
		}.bind(this)) : $.each(this.$(".traffic_poi"),
		function(t, e) {
			$(e).on("click",
			function() {
				this.end_id = $(e).attr("id"),
				this.end_name = $(e).attr("name"),
				transferType = "MinTransfer",
				this.$("div.confirm_end").html('<font color="red">终点：</font>' + this.end_name),
				Jr.Navigator.navigate("#traffic/schemas/" + this.start_id + "&" + this.end_id + "&" + encodeURIComponent(this.start_name) + "&" + encodeURIComponent(this.end_name), {
					trigger: !0,
					animation: {
						type: Jr.Navigator.animations.SLIDE_STACK,
						direction: Jr.Navigator.directions.LEFT
					}
				})
			}.bind(this))
		}.bind(this)),
		$.iScroll()
	},
	initPagination: function() {
		this.$("div.c_pagination").css("display", "none"),
		this.currentPage = 0,
		this.totalPages = 0,
		this.pageSize = 10
	},
	events: {
		"click .button-positive": "onButtonPrev",
		"click .page_next": "onPageNext",
		"click .page_prev": "onPagePrev"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#traffic", {
			trigger: !0
		}) : window.history.back()
	},
	onPageNext: function() {
		this.currentPage++,
		this.afterRender()
	},
	onPagePrev: function() {
		this.currentPage--,
		this.afterRender()
	}
});
SchemasTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_margin" >返回</a>', '<h1 class="title">换乘方案</h1>', "</header>", '<div class="bar-standard bar-tools-traffic" >', '<div class="confirm_start"><font color="green">起点：</font>三元坊</div>', '<div class="confirm_end"><font color="red">终点：</font>观前街</div>', "</div>", '<nav class="bar-standard" style="top:87px;">', '<ul class="segmented-controller" style="width:100%;">', '<li class="" s_type="MinTransfer">', '<a style="text-overflow:clip;">较便捷</a>', "</li>", '<li s_type="LeastWalkTransfer">', '<a style="text-overflow:clip;">少步行</a>', "</li>", '<li s_type="LeastTimesTransfer">', '<a style="text-overflow:clip;">少换乘</a>', "</li>", '<li s_type="BusTransfer">', '<a style="text-overflow:clip;">只坐公交</a>', "</li>", "</ul>", "</nav>", '<div class="content content-position-traffic" style="top:131px;">', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var SchemasView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.key = hash.substring(hash.lastIndexOf("/") + 1);
		var t = this.key.split("&");
		this.start_id = decodeURIComponent(t[0]),
		this.end_id = decodeURIComponent(t[1]),
		this.start_name = decodeURIComponent(t[2]),
		this.end_name = decodeURIComponent(t[3]),
		this.s_type = transferType,
		this.start_type = "pid",
		"我的位置" == this.start_name && (this.start_type = "ll")
	},
	render: function() {
		$("li.tab-item").removeClass("active"),
		$($("li.tab-item")[1]).addClass("active"),
		this.$el.html(SchemasTemplate);
		var t = this.$("div.bar-tools-traffic");
		return t.html('<div class="confirm_start"><font color="green">起点：</font>' + this.start_name + "</div>" + '<div class="confirm_end"><font color="red">终点：</font>' + this.end_name + "</div>").css("display", "block"),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		setTimeout(this.addControllerEvent.bind(this), 500),
		this
	},
	addControllerEvent: function() {
		schemasTypes = $("ul.segmented-controller").find("li"),
		schemasTypes.on("click",
		function(t) {
			$(t.currentTarget).hasClass("active") || ($("ul.segmented-controller").find("li.active").removeClass("active"), $(t.currentTarget).addClass("active"), this.s_type = $(t.currentTarget).attr("s_type"), transferType = this.s_type, this.afterRender())
		}.bind(this)),
		$.each(schemasTypes,
		function(t, e) {
			$(e).attr("s_type") == this.s_type && $(e).addClass("active")
		}.bind(this))
	},
	afterRender: function() {
		$("#ajax_loading").css({
			display: "block",
			top: "141px"
		});
		var t = "http://api.sz-map.com/sip/ts?m=srbfat&type=TOUCH&rst=" + this.s_type + "&s=a&f=json&sc=5&ft=1&fv=" + this.start_id + "&&tt=1&tv=" + this.end_id + "&t=" + timeStamp;
		if ("ll" == this.start_type) var t = "http://api.sz-map.com/sip/ts?m=srbfat&type=TOUCH&rst=" + this.s_type + "&s=a&f=json&sc=5&ft=3&fv=" + this.start_id + "&&tt=1&tv=" + this.end_id + "&t=" + timeStamp;
		$.ajax({
			url: t,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning_traffic")
			}.bind(this),
			success: function(t) {
				this.result = t,
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning_traffic")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.schemas,
		i = '<ul class="list customizeList_station">';
		$.each(e,
		function(t, e) {
			var n = e.title,
			a = e.distance;
			i += '<li><a class="traffic_schema" ><strong>' + n + "</strong>" + "<p>" + a + "</p>" + "</li>"
		}),
		i += "</ul>",
		this.$("div.search_result").html(i),
		$.each(this.$(".traffic_schema"),
		function(t, e) {
			$(e).on("click",
			function() {
				Jr.Navigator.navigate("#traffic/detail/" + this.start_id + "&" + this.end_id + "&" + encodeURIComponent(this.start_name) + "&" + encodeURIComponent(this.end_name) + "&" + t, {
					trigger: !0,
					animation: {
						type: Jr.Navigator.animations.SLIDE_STACK,
						direction: Jr.Navigator.directions.LEFT
					}
				})
			}.bind(this))
		}.bind(this)),
		$.iScroll()
	},
	events: {
		"click .button-positive": "onButtonPrev"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#traffic", {
			trigger: !0
		}) : window.history.back()
	}
});
DetailTemplate = ['<header class="bar-title bar-header">', '<a class="button-positive btn_margin">返回</a>', '<h1 class="title"></h1>', "</header>", '<div class="bar-standard bar-tools">', "</div>", '<div class="content content-position">', '<div class="search_result" id="scroller">', "</div>", "</div>"].join("\n");
var DetailView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.hash = hash,
		this.key = hash.substring(hash.lastIndexOf("/") + 1);
		var t = this.key.split("&");
		this.start_id = decodeURIComponent(t[0]),
		this.end_id = decodeURIComponent(t[1]),
		this.start_name = decodeURIComponent(t[2]),
		this.end_name = decodeURIComponent(t[3]),
		this.seq = decodeURIComponent(t[4]),
		this.s_type = transferType,
		this.start_type = "pid",
		"我的位置" == this.start_name && (this.start_type = "ll")
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[1]).addClass("active"),
		this.$el.html(DetailTemplate),
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "block");
		var t = "http://api.sz-map.com/sip/ts?m=srbfat&type=TOUCH&rst=" + this.s_type + "&s=a&f=json&sc=5&ft=1&fv=" + this.start_id + "&&tt=1&tv=" + this.end_id + "&t=" + timeStamp;
		if ("ll" == this.start_type) var t = "http://api.sz-map.com/sip/ts?m=srbfat&type=TOUCH&rst=" + this.s_type + "&s=a&f=json&sc=5&ft=3&fv=" + this.start_id + "&&tt=1&tv=" + this.end_id + "&t=" + timeStamp;
		$.ajax({
			url: t,
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {
				$("#ajax_loading").css("display", "none")
			},
			error: function() {
				this.$("div.content").addClass("warning")
			}.bind(this),
			success: function(t) {
				t.success ? this.initList(t) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.afterRender)) : this.$("div.content").addClass("warning")
			}.bind(this)
		})
	},
	initList: function(t) {
		var e = t.schemas,
		i = e[this.seq],
		n = i.title,
		a = i.distance,
		o = i.keyPoints,
		s = i.keyPolylines,
		r = this.$("div.bar-tools");
		r.html("全程" + a).css("display", "block");
		var c = '<ul class="list customizeList">';
		this.$("h1.title").html(n);
		for (var l = 0; l < o.length; l++) 0 == l ? c += "2" == s[l].type ? "ll" == this.start_type ? '<li><div class="tmore b_start">从<font color="red" style="padding:5px;">我的位置</font>出发</div></li>': '<li><div class="tmore b_start">从<a href="#/lid/' + s[l].lguid + '" style="color:red;display:inline;text-decoration:underline;padding:5px 5px;">' + o[0].name + "</a>出发</div></li>": "ll" == this.start_type ? '<li><div class="tmore b_start">从<font color="red" style="padding:5px;">我的位置</font>出发</div></li>': '<li><div class="tmore b_start">从<font color="red" style="padding:5px;">' + o[0].name + "</font>出发</div></li>": l < o.length - 1 ? (c += "1" == s[l - 1].type ? '<li><div class="tmore b_walk">' + s[l - 1].name: "3" == s[l - 1].type ? '<li><div class="tmore b_metro">乘坐<font style="font-weight:bolder;padding:5px 5px;">' + s[l - 1].name + "</font>" + s[l - 1].description + "下车，": '<li><div class="tmore b_bus">乘坐<a href="#/lid/' + s[l - 1].lguid + '" style="display:inline;text-decoration:underline;padding:5px 5px;">' + s[l - 1].name + "</a>" + s[l - 1].description + "下车，", c += "2" == s[l].type ? '&nbsp;到达<a style="text-decoration:underline;" href="#/lid/' + s[l].lguid + '">' + o[l].name + "</a>" + o[l].pointPosition + "</div></li>": '&nbsp;到达<font style="font-weight:bolder;">' + o[l].name + "</font>" + o[l].pointPosition + "</div></li>", l == s.length - 1 && (c += "1" == s[l].type ? '<li><div class="tmore b_walk">' + s[l].name + "</div></li>": "2" == s[l].type ? '<li><div class="tmore b_bus">乘坐<a href="#/lid/' + s[l].lguid + '" style="text-decoration:underline;">' + s[l].name + "</a>" + s[l].description + "下车</div></li>": '<li><div class="tmore b_metro">乘坐<font style="font-weight:bolder;">' + s[l].name + "</font>" + s[l].description + "下车</div></li>")) : c += '<li><div class="tmore b_end">到达<font color="green" style="padding:5px;">' + o[o.length - 1].name + "</font></div></li>";
		c += "</ul>",
		this.$("div.search_result").html(c),
		$.iScroll()
	},
	events: {
		"click .button-positive": "onButtonPrev"
	},
	onButtonPrev: function() {
		1 == window.history.length ? Backbone.history.navigate("#traffic", {
			trigger: !0
		}) : window.history.back()
	}
}),
HomeTemplate = ['<div class="content">', '<div id="map">', "</div>", '<div class="research_btn"></div>', "</div>"].join("\n"),
HomeView = Jr.View.extend({
	initialize: function() {
		hash = window.location.hash,
		this.lonlat = hash.substring(hash.lastIndexOf("/") + 1),
		-1 != this.lonlat.indexOf("#me") && (this.lonlat = "")
	},
	render: function() {
		return $("li.tab-item").removeClass("active"),
		$($("li.tab-item")[2]).addClass("active"),
		this.$el.html(HomeTemplate),
		this.markers = [],
		this.requestTimes = 0,
		setTimeout(this.afterRender.bind(this), 500),
		this
	},
	afterRender: function() {
		$("#ajax_loading").css("display", "none"),
		this.initMap(),
		"" == this.lonlat ? this.loadScreenStations() : this.loadStationByLonlat()
	},
	initMap: function() {
		var t = L.CRS.SZMAP,
		e = new L.LatLng(30.75227, 120.17251),
		i = new L.LatLng(32.08858, 121.37402),
		n = new L.LatLngBounds(e, i),
		a = new L.Map("map", {
			crs: t
		});
		this.map = a,
		a.setMaxBounds(n);
		var o = "http://61.155.169.26:8399/arcgis/rest/services/Layers20130319_retina/MapServer/tile/{z}/{y}/{x}",
		s = "Map data &copy; 2013 Gaeainfo",
		r = new L.TileLayer(o, {
			maxZoom: 19,
			minZoom: 12,
			attribution: s,
			errorTileUrl: "http://61.155.169.26:8399/arcgis/rest/services/Layers20121112/MapServer/tile/2/7549/10674",
			levelOffset: 12,
			detectRetina: !0
		});
		a.addLayer(r);
		var c = "120.581058",
		l = "31.301156";
		start = new L.LatLng(l, c),
		a.setView(start, 17)
	},
	loadScreenStations: function() {
		if (navigator.geolocation) {
			var t = {
				enableHighAccuracy: !0,
				maximumAge: 1e4,
				timeout: 45e3
			};
			navigator.geolocation.getCurrentPosition(function(t) {
				x = t.coords.longitude,
				y = t.coords.latitude;
				var e = L.icon({
					iconUrl: "images/location/me.png",
					iconRetinaUrl: "images/location/me@2x.png",
					iconSize: [50, 57]
				});
				start = new L.LatLng(y, x),
				this.map.setView(start, 17),
				L.marker([y, x], {
					icon: e
				}).bindLabel("您的位置", {
					noHide: !0
				}).addTo(this.map).showLabel(),
				$("#map_tips").html("正在加载周边公交站台"),
				$("#map_tips").animate({
					opacity: 1
				});
				var i = this.map.getBounds(),
				n = i.getNorthWest(),
				a = i.getSouthEast(),
				o = $.encodeLonLat({
					x: n.lng,
					y: n.lat
				}),
				s = $.encodeLonLat({
					x: a.lng,
					y: a.lat
				});
				$.ajax({
					url: "http://api.sz-map.com/sip/p?m=stsbxy&type=TOUCH&rb=" + s + "&lt=" + o + "&t=" + timeStamp + "&f=json&s=a",
					async: !0,
					dataType: "jsonp",
					timeout: 3e4,
					complete: function() {},
					success: function(t) {
						t.success ? ($("#map_tips").html("点击图标可查看途经公交"), this.loadStations(t)) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.loadScreenStations)) : $("#map_tips").html("未查询到周边公交站台")
					}.bind(this)
				})
			}.bind(this),
			function() {},
			t)
		}
	},
	loadStationByLonlat: function() {
		var t = this.lonlat.split("&"),
		e = t[0],
		i = t[1],
		n = L.icon({
			iconUrl: "images/location/me.png",
			iconRetinaUrl: "images/location/me@2x.png",
			iconSize: [50, 57]
		});
		start = new L.LatLng(i, e),
		this.map.setView(start, 17),
		L.marker([i, e], {
			icon: n
		}).bindLabel("您的位置", {
			noHide: !0
		}).addTo(this.map).showLabel(),
		$("#map_tips").html("正在加载周边公交站台"),
		$("#map_tips").animate({
			opacity: 1
		});
		var a = this.map.getBounds(),
		o = a.getNorthWest(),
		s = a.getSouthEast(),
		r = $.encodeLonLat({
			x: o.lng,
			y: o.lat
		}),
		c = $.encodeLonLat({
			x: s.lng,
			y: s.lat
		});
		$.ajax({
			url: "http://api.sz-map.com/sip/p?m=stsbxy&type=TOUCH&rb=" + c + "&lt=" + r + "&t=" + timeStamp + "&f=json&s=a",
			async: !0,
			dataType: "jsonp",
			timeout: 3e4,
			complete: function() {},
			success: function(t) {
				t.success ? ($("#map_tips").html("点击图标可查看途经公交"), this.loadStations(t)) : "100" == t.errorCode && 0 == this.requestTimes ? (this.requestTimes++, $.requestTM(this, this.loadStationByLonlat)) : $("#map_tips").html("未查询到周边公交站台")
			}.bind(this)
		})
	},
	loadStations: function(t) {
		var e = t.pois.poi,
		i = null,
		n = L.icon({
			iconUrl: "images/location/bus.png",
			iconRetinaUrl: "images/location/bus@2x.png",
			iconSize: [25, 34]
		});
		this.markers && this.markers.length > 0 && ($.each(this.markers,
		function(t, e) {
			this.map.removeLayer(e)
		}.bind(this)), this.markers = []),
		$.each(e,
		function(t, e) {
			var a = $.decodeLonLat(e.geometry)[0],
			o = new L.LatLng(a.y, a.x),
			s = L.marker(o, {
				icon: n
			});
			this.markers.push(s),
			s.addTo(this.map),
			s.on("click",
			function() {
				null != i && (i.hideLabel(), i.setZIndexOffset(0)),
				i = s;
				var t = "#/sl/" + encodeURIComponent(e.name) + "&" + e.scode + "&" + e.guid,
				n = '<a class="map_link" href="' + t + '">' + e.name + "</a>";
				s.bindLabel(n, {
					noHide: !0
				}).addTo(this.map).showLabel(),
				s.setZIndexOffset(1e3)
			}.bind(this))
		}.bind(this))
	}
}),
AppRouter = Jr.Router.extend({
	routes: {
		"": "bus",
		"s/:query": "searchStation",
		"sl/:query": "stationLine",
		"lid/:query": "lineStation",
		"l/:query": "searchLine",
		traffic: "traffic",
		"traffic/confirm/:query": "confirmStartEnd",
		"traffic/schemas/:query": "schemas",
		"traffic/detail/:query": "detail",
		"me/:query": "home",
		me: "home"
	},
	initialize: function() {
		return this.bind("all", this._trackBaiduPageview),
		this.bind("all", this._trackPageview),
		this.bind('all', this._trackAPI)
	},
	_trackAPI: function() {
		$.ajax({
			url: 'http://api.sz-map.com/log/maps/sc/spv?type=TOUCH&f=json',
			async: true,
			dataType: 'jsonp'
		});
	},
	_trackPageview: function() {
		var t;
		return t = "/cx/#/" + Backbone.history.getFragment(),
		console.log(t),
		_gaq.push(["_trackPageview", t])
	},
	_trackBaiduPageview: function() {
		var t;
		return t = "/cx/#/" + Backbone.history.getFragment(),
		_hmt.push(["_trackPageview", t])
	},
	bus: function() {
		var t = new BusView;
		this.renderView(t)
	},
	searchStation: function() {
		var t = new SearchStationView;
		this.renderView(t)
	},
	stationLine: function() {
		var t = new StationLineView;
		this.renderView(t)
	},
	lineStation: function() {
		var t = new LineStationView;
		this.renderView(t)
	},
	searchLine: function() {
		var t = new SearchLineView;
		this.renderView(t)
	},
	traffic: function() {
		var t = new TrafficView;
		this.renderView(t)
	},
	confirmStartEnd: function() {
		var t = new ConfirmStartEndView;
		this.renderView(t)
	},
	schemas: function() {
		var t = new SchemasView;
		this.renderView(t)
	},
	detail: function() {
		var t = new DetailView;
		this.renderView(t)
	},
	home: function() {
		var t = new HomeView;
		this.renderView(t)
	}
}),
appRouter = new AppRouter;
Backbone.history.start();