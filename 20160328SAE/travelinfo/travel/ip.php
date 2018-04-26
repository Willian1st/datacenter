<?php
include ("../php/log4all.php");
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="stylesheet" href="../plugins/jquery.mobile.css">
<script src="../plugins/jquery.js"></script>
<script src="../plugins/jquery.mobile.js"></script>
<script type="text/javascript" src="js/is_mobile.js"></script>
<script type="text/javascript" src="js/ip.js"></script>
<title>IP</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>IP查询</h1>
		</div>
		<!-- <fieldset data-role="controlgroup">
			<label for="host">本机</label> <input type="radio" id="host"
				value="host" name="ip" checked> <label for="other">指定IP</label>
			<input type="radio" id="other" name="ip" value="other">
		</fieldset> -->
		<div data-role="fieldcontain">
			<input type="search" name="search" id="search_text" hidden="true"
				placeholder="搜索内容...">
		</div>
		<input id="search" type="button" value="提交">
		<ul id="list" data-role="listview" data-inset="true">
			<!-- <li data-role="list-divider" id="result">当前IP信息</li> -->
			<li>提供商:<span id="ip_isp" class="ui-li-count"></span></li>
			<li>城市:<span id="ip_city" class="ui-li-count"></span></li>
			<li>省:<span id="ip_province" class="ui-li-count"></span></li>
			<li>国家:<span id="ip_country" class="ui-li-count"></span></li>
			<li>类型:<span id="ip_type" class="ui-li-count"></span></li>
			<li>区域:<span id="ip_district" class="ui-li-count"></span></li>
			<li>其他:<span id="ip_desc" class="ui-li-count"></span></li>
			<li>开始地址段:<span id="ip_start" class="ui-li-count"></span></li>
			<li>结束地址段:<span id="ip_end" class="ui-li-count"></span></li>
		</ul>
		<div data-role="footer" data-position="fixed">
			<h1>IP查询</h1>
		</div>
	</div>
</body>
</html>