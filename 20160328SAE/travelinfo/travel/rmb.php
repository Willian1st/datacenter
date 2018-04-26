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
<script type="text/javascript" src="js/rmb.js"></script>
<title>RMB</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>RMB大写</h1>
		</div>
		<div data-role="fieldcontain">
			<input type="search" name="search" id="num" hidden="true"
				placeholder="阿拉伯数字...">
		</div>
		<input id="convert" type="button" value="转换">
		<ul id="list" data-role="listview" data-inset="true">
			<li>人民币大写:<span id="result" class="ui-li-count"></span></li>
		</ul>
		<div data-role="footer" data-position="fixed">
			<h1>RMB大写</h1>
		</div>
	</div>
</body>
</html>