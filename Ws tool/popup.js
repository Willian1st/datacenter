$(function () {
	$("[name='user']").on("change", function () {
		sendMessage($(this).val());
	});
	$(".btn-clean").on("click", function () {
		sendMessage("clean");
	});
	$("#popup").on("submit", function () {
		sendMessage($("[name='user']").val());
	});
	$("#popup").on("reset", function () {
		sendMessage("logout");
	});
	var sendMessage = function (user) {
		// 给content_script/background发送信息
		chrome.extension.sendRequest({
			cmd: user
		}, function (response) {
			debugger;
			console.log(response.result);
		});
		//
		chrome.tabs.query({
			active: true,
			currentWindow: true
		}, function (tabs) {
			chrome.tabs.sendMessage(tabs[0].id, {
				message: user
			}, function (response) {
				debugger
			});
		});
	}
});
