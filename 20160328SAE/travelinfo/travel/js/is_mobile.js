$(function() {
	var url = window.location.href;
	if (url.indexOf("travelinfo.sinaapp.com") >= 0) {
		if (!navigator.userAgent
				.match(/(iPhone|iPod|Android|ios|iPad|Windows Phone)/i)) {
//			document
//					.write("<div style='text-align:center'>使用移动设备登录以获得更好的体验</div>");
//			confirm("请使用移动设备登录...");
		} else {
			// local visit
			localStorage.local = "0";
		}

		$(function() {
			$(
					'<div id="home" style="position:fixed;left:20px;bottom:60px;"><a href="#" style="text-decoration: none;">返回首页</a></div>')
					.insertBefore(".ui-footer");
			$("#home").on("click", function() {
				if (localStorage.local == "0") {
					window.location.href = "/welcome.php";
				}
				window.location.href = "/welcome";
			});
		});
	}

});