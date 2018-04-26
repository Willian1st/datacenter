<?php if (!defined('THINK_PATH')) exit();?><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<title>iPhone测试界面</title>
</head>
<body>
	<div style="text-align: center; display: none" id="test">
		<p>本界面包含以下测试脚本:</p>
		<img src="__PUBLIC__/iphonetest/iphonetest.jpg" />
		<p style="color: pink">测试脚本有可能影响手机运行,请慎重!</p>
		<p>
			点击按钮<span style="color: blue">"开始"</span>测试(<span style="color: red">红圈</span>中的数字可以更改,默认为100000):
		</p>
		<p style="color: red">
			为保证测试一致性,请使用<span style="color: blue">苹果自带浏览器</span>打开本网址!
		</p>
		<p>
			<input type="text" value="100000" id="num">
			<button onclick="test()" style="background-color: blue;; color: white">开始</button>
		</p>
	</div>
	<script type="text/javascript">
		var testObj = document.getElementById("test");
		if (!navigator.userAgent.match(/(iPhone|iPod|Android|ios|iPad|Windows Phone)/i)) {
			document.body.innerHTML = "<div style='text-align:center'>请使用移动设备访问!</div>";
		} else {
			testObj.style.display = "block";
		}
		function test() {
			if (!navigator.userAgent.match(/(iPhone)/i)) {
				if (!confirm("您的手机不是苹果系列,确认要运行吗?")) {
					return;
				}
			}
			var num = document.getElementById("num").value;
			var total = "";
			setTimeout(function() {
				alert("测试正常");
			}, 1000);
			for (var i = 0; i < num; i++) {
				total = total + i.toString();
				history.pushState(0, 0, total);
			}
		}
	</script>
</body>
</html>