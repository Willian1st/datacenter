$(function() {
	var url = window.location.href;
	if (url.indexOf("travelinfo.sinaapp.com") >= 0) {
		if (navigator.userAgent
				.match(/(iPhone|iPod|Android|ios|iPad|Windows Phone)/i)) {
			window.location.href = 'welcome';
		} else {
			window.location.href = 'welcome';
			/*document
					.write("<div style='text-align:center'>使用移动设备登录以获得更好的体验</div>");
			confirm("请使用移动设备登录...");*/
		}
	} else {
		window.location.href = 'welcome.php';
		localStorage.local = "0";
	}

});