$(function() {
	window.alert = layer.msg;
	hotkeys('shift+a', function(event, handler) {
		submit('admin02');
	});
	hotkeys('shift+y', function(event) {
		submit('sys');
	});
	hotkeys('shift+s', function(event) {
		submit('sysAdmin');
	});
	hotkeys('fn+s', function(event) {
		submit('sysAdmin', "zq123456");
	});
	hotkeys('shift+m', function(event) {
		submit('mlmkAdmin');
	});
	hotkeys('shift+w', function(event) {
		submit('wfhg');
	});
	hotkeys('shift+b', function(event) {
		submit('bjyyt');
	});
	hotkeys('shift+l', function(event) {
		submit('lpsAdmin');
	});
	hotkeys('shift+z', function(event) {
		submit('lps_zsq_Admin');
	});
	hotkeys('shift+g', function(event) {
		submit('gzrjaqkj');
	});
	hotkeys('fn+esc', function() {
		var button = document.getElementById("logoutHref");
		button.click();
	});
	hotkeys('alt+1', function() {
		location.href = "http://localhost:7070/cas/login";
	});
	hotkeys('alt+2', function() {
		location.href = "http://www.gzaqy.com/cas/login";
	});

	hotkeys('alt+h', function() {
		chrome.extension.sendRequest({
			cmd : "history"
		}, function(response) {
			console.log(response.result);
		});
	});
	hotkeys('shift+tab', function() {
		chrome.extension.sendRequest({
			cmd : "tab"
		}, function(response) {
			console.log(response.result);
		});
	});
	hotkeys('shift+ctrl+alt+s', function() {
		// 打卡-suning
		chrome.extension.sendRequest({
			cmd : "suning"
		}, function(response) {
			return;
			console.log(response.result);
		});
	});

	hotkeys('shift+q', function() {
		// 打卡-guomei
		chrome.extension.sendRequest({
			cmd : "guomei"
		}, function(response) {
			debugger
			console.log(response.result);
			return;
			setTimeout(function() {
				if ($("#tabRight")) {
					$("#tabRight").click();
				}
			}, 3000);
		});
	});

});
submit = function(userName, passWord) {
	var users = [ {
		name : 'sys',
		code : 'longruan++'
	}, {
		name : 'sysAdmin',
		code : 'aqy+123'
	}, {
		name : 'admin02',
		code : 'admin02'
	}, {
		name : 'mlmkAdmin',
		code : '123456'
	}, {
		name : 'wfhg',
		code : '123456'
	}, {
		name : 'gzrjaqkj',
		code : '123456'
	}, {
		name : 'lpsAdmin',
		code : '123456'
	}, {
		name : 'lps_zsq_Admin',
		code : '123456'
	}, {
		name : 'zyajj',
		code : '123456'
	}, {
		name : 'bjsajj',
		code : '123456'
	}, {
		name : 'sysAdmin_zq',
		code : 'zq123456'
	}, {
		name : 'bjyyt',
		code : '888888'
	}, {
		name : 'bzqmtzajbckm',
		code : '123456'
	}	];
	setTimeout(
			function() {
				var username = $("[name='username']");
				if (username.length == 0) {
					username = $(document.getElementsByClassName('username')[0]);
				}
				if (username.length == 0) {
					username = document.getElementsByTagName('iframe')[0].contentWindow.document
							.getElementById('username');
				}
				var password = $("[name='password']");
				if (password.length == 0) {
					password = $(document.getElementsByClassName('password')[0]);
				}
				if (password.length == 0) {
					password = document.getElementsByTagName('iframe')[0].contentWindow.document
							.getElementById('password');
				}
				if (!(username instanceof jQuery)) {
					username = $(username);
				}
				if (!(password instanceof jQuery)) {
					password = $(password);
				}
				username.focus().val('');
				password.focus().val('');
				var form = document.getElementById('fm1')
						|| document.getElementById('fm11');
				if (!form) {
					form = document.getElementsByClassName('login-btn')[0];
				}
				if (!form) {
					form = document.getElementsByTagName('iframe')[0].contentWindow.document
							.getElementById('fm1');
				}
				username.val(userName.replace("_zq", ""));
				password.val(passWord || (function(userName) {
					var code = '';
					$.each(users, function(i, v) {
						if (v.name == userName) {
							code = v.code;
						}
					});
					return code;
				})(userName));
				if (form) {
					if (form.submit) {
						form.submit();
					} else {
						form.click();
					}
					var gointo = document.getElementsByTagName('iframe')[0].contentWindow.document
							.getElementById('go-success');
					setInterval(
							function() {
								if (gointo) {
									gointo.click();
								} else {
									gointo = document
											.getElementsByTagName('iframe')[0].contentWindow.document
											.getElementById('go-success');
								}
							}, 1000);
				}
			}, 1000);
};
sendMessage = function(keys) {
	// 给background和popup发送数据
	chrome.extension.sendRequest({
		cmd : JSON.stringify(keys)
	}, function(response) {
		return;
		console.log(response.result);
	});
}
// 接收popup发来的消息
chrome.extension.onMessage.addListener(function(request, sender, sendResponse) {
	if (request.message == "logout") {
		var button = document.getElementById("logoutHref");
		if (button) {
			button.click();
			layer.msg("退出");
		}
	} else if (request.message == "clean") {
		var url = location.href;
		if (url) {
			var reg = new RegExp("(^|;)jsessionid=([^#]*)");
			var r = url.substr(1).match(reg);
			if (r != null) {
				var result = ";jsessionid=" + unescape(r[2]);
				history.pushState(null, null, url.replace(result, ""));
			}
		}
	} else if (request.message) {
		submit(request.message);
	}
});
// 获取url中的参数
function getUrlParam(name) {
	var reg = new RegExp("(^|;)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
if (location.href.indexOf("localhost") == -1) {
	// $("body").append("<style>h1,h2,h3,h4,h5,p,body,div,span,label,a,i,form,table,th,tr,td,ul,li,figure,pre,button,header,code,textarea{background-color:#2b2b2b!important;color:#bbbbbb;}</style>");
}
$(function() {
	return;
	// guomei start
	if ($(".tabRight")) {
		setTimeout(function() {
			// 切换登录框
			$(".tabRight").click();
			setTimeout(function() {
				// 点击登录
				// $("#loginName").val(15195772530);
				// $("#loginPassword").val("");
				$(".actions .btnnuw").click();
			}, 1000);
		}, 1000);

	} else {
		console.log("登录框不存在");
	}
	if ($(".sign-btn,.sign-btn-text")) {
		setTimeout(
				function() {
					// 点击签到
					$(".sign-btn,.sign-btn-text").click();
					if ($(".sign-btn,.sign-btn-text")) {
						setTimeout(
								function() {
									// 进入抽奖界面
									if ($(".mdcj") && $(".mdcj")[0]) {
										location.href = "//prom.gome.com.cn/html/prodhtml/topics/201608/11/8952408833.html";

									}
								}, 1000);
					} else {
						console.log("抽奖链接不存在");
					}
				}, 1000);

	} else {
		console.log("签到不存在");
	}

	if ($("#m") || $("#m")[0]) {
		setTimeout(
				function() {
					// 点击抽奖
					if ($(".luck .bt")) {
						setTimeout(function() {
							$(".luck .bt").click();
						}, 1000);
						setTimeout(
								function() {
									// 点击关闭
									if ($("#showInfo .j-gl-c")
											&& $("#showInfo .j-gl-c")[0]) {
										$("#showInfo .j-gl-c")[0].click();
										setTimeout(
												function() {
													// 点击抽奖
													if ($(".luck .bt")) {
														$(".luck .bt").click();
														setTimeout(
																function() {
																	// 点击关闭
																	if ($("#showInfo .j-gl-c")) {
																		$("#showInfo .j-gl-c")[0]
																				.click();
																		setTimeout(
																				function() {
																					// 退出
																					if ($(".gome-link-logout")[0]) {
																						location.href = "//login.gome.com.cn/login?type=logout";
																					}
																				},
																				1000);
																	}
																}, 8000);
													}
												}, 1000);
									}
								}, 8000);
					} else {
						console.log("抽奖按钮不存在");
					}
				}, 1000);
	} else {
		console.log("界面不存在");
	}
	// guomei end
	if ($("#popBox-login-button") && $("#popBox-login-button")[0]) {
		$("#popBox-login-button")[0].click();
	} else {
		console.log("10086登录按钮不存在");
	}
	if ($("#doSign") && $("#doSign")[0]) {
		$("#doSign")[0].click();
	} else {
		console.log("e币按钮不存在");
	}
});
