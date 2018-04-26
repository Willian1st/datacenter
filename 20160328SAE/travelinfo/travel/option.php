<?php
include ("../php/log4all.php");
?>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<script src="../plugins/jquery.js"></script>
<script src="../plugins/jquery.tagcanvas.js"></script>
<script type="text/javascript" src="js/is_mobile.js"></script>
<script type="text/javascript" src="js/option.js"></script>
<style type="text/css">
body {
	background: #2a2b2d;
	font: 13px/1.3 'Microsoft Yahei';
	color: #999;
}

#canvasContainer {
	text-align: center;
}

#myCanvas {
	margin-top:100px;
	width: 100%;
}
</style>
<title>兼容性测试</title>
</head>
<body>
			<div class="container">
				<div id="canvasContainer">
					<canvas id="myCanvas"></canvas>
				</div>
				<div id="tags" style="display: none;">
					<ul>
						<li><a href="#" data-icon="plus" id="weather_info">天气查询</a></li>
						<li><a href="#" data-icon="minus" id="ip_info">IP地址查询</a></li>
						<li><a href="#" data-icon="delete" id="color-info">颜色查询</a></li>
						<li><a href="#" data-icon="check" id="url">下载地址解析</a></li>
						<li><a href="#" data-icon="info" id="position">位置信息</a></li>
						<li><a href="#" data-icon="forward" id="rmb">人民币大写</a></li>
						<li><a href="#" data-icon="back">-</a></li>
						<li><a href="#" data-icon="star">-</a></li>
						<li><a href="#" data-icon="gear" id="option">选项</a></li>
						<li><a href="#" data-icon="search" id="bus_info">实时公交</a></li>
					</ul>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						var _canvas = document.createElement('canvas');  
						 if (_canvas.getContext){
						 	
						 }else{
							alert("您的浏览器无法显示本界面内容!");
						}
						
						if (!$('#myCanvas').tagcanvas({
							textColour : "#fff",
							outlineColour : '#c2c2c2',
							reverse : true,
							depth : 0.8,
							maxSpeed : 0.05,
							lock : 'x',
							initial : [ 0, 0.2 ]
						}, 'tags')) {
							$('#canvasContainer').hide();
						}
		
					});
					
				</script>
			</div>
</body>
</html>