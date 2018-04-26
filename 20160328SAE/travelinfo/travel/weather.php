<?php
include ("../php/log4all.php");

?>
<!DOCTYPE html>
<html>
<head>
<meta charset="GBK">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../plugins/jquery.mobile.css">
<script src="../plugins/jquery.js"></script>
<script src="../plugins/jquery.mobile.js"></script>
<script type="text/javascript" src="js/is_mobile.js"></script>
<script type="text/javascript" src="js/weather.js"></script>
<title>天气查询</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>天气查询</h1>
		</div>
		<div data-role="fieldcontain">
			<input type="search" name="search" id="search_text" hidden="true"
				placeholder="输入城市名称,例如:北京...">
		</div>
		<input id="search" type="button" value="提交">
		<ul id="list" data-role="listview" data-inset="true">
			<li><span id="defalut"></span><span id="city"
				class="ui-li-count"></span></li>
			<li>天气:<span id="status" class="ui-li-count"></span></li>
			<li>温度:<span id="temp" class="ui-li-count"></span></li>
			<li>风力:<span id="wind" class="ui-li-count"></span></li>
		</ul>
		<div data-role="footer" data-position="fixed">
			<h1>天气查询</h1>
		</div>
	</div>
</body>
</html>