$(function() {
	if (navigator.userAgent.match(/(MicroMessenger)/i)) {
		window.location.href = window.location.href + '/shake';
	} else {
		document.write("<div style='text-align:center'>本界面只能在微信中打开(⊙o⊙)哦</div>");
	}
});