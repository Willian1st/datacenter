<?php
include ("php/log4all.php");
?>
<head>
<meta name="viewport" content="width=device-width">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<link rel="stylesheet" href="plugins/jquery.mobile.css">
<link rel="stylesheet" href="travel/css/fixleft.css">
<link rel="icon" href="new/spyonyou.png">
<script src="plugins/jquery.js"></script>
<script src="plugins/jquery.mobile.js"></script>
<script type="text/javascript" src="travel/js/is_mobile.js"></script>
<script type="text/javascript" src="travel/js/welcome.js"></script>
<script type="text/javascript" src="plugins/jquery.tagcanvas.js"></script>
<style type="text/css">
#real-time {
	text-align: center;
	display: none;
}

.ui-li-count {
	font-size: 10px;
	border-style: none;
}

.foot{
	font-size: 13px;
	padding:5px;
}

#logo{
	position: relative;
	top: 5px;
}
</style>
<title>欢迎</title>
</head>
<body>
	<div data-role="page" id="pageone">
		<div data-role="header" data-position="fixed">
			<h1>信息查询</h1><a id="toNew" href="#" data-role="button" data-inline="true">体验新版</a>
		</div>

		<div data-role="content">
			<div data-role="navbar">
				<ul>
					<li><a href="#" data-icon="plus" id="weather_info">天气查询</a></li>
					<li><a href="#" data-icon="minus" id="ip_info">IP地址查询</a></li>
					<li><a href="#" data-icon="delete" id="color-info">颜色查询</a></li>
					<li><a href="#" data-icon="check" id="url">下载地址解析</a></li>
					<li><a href="#" data-icon="info" id="position">位置信息</a></li>
					<li><a href="#" data-icon="forward" id="rmb">人民币大写</a></li>
					<li><a href="#" data-icon="back" id="view">视差滚动</a></li>
					<li><a href="#" data-icon="star" id="trans">翻译</a></li>
					<li><a href="#" data-icon="gear" id="option">选项</a></li>
					<li><a href="#" data-icon="search" id="bus_info">实时公交</a></li>
					<li><a href="php/img2base64.php" data-icon="search" id="picture">图片编码</a></li>
				</ul>
			</div>
			
			<div id="floatPanel">
				<div class="ctrolPanel" >
					<a class="arrow" href="#"><span>顶部</span></a>
					<a class="contact" href="#" target="_blank"><span>反馈</span></a>
					<a class="qrcode" href="#"><span>微信二维码</span></a>
					<a class="arrow" href="#"><span>底部</span></a>
				</div>
				
				<div class="popPanel">
					<div class="popPanel-inner">
						<div class="qrcodePanel"><img src="/travel/img/xxzx.jpg" /><p>扫描二维码关注公众号</p></div>
						<div class="arrowPanel">
							<div class="arrow01"></div>
							<div class="arrow02"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div data-role="footer" data-position="fixed">
			<div class="foot">Since 10/08, powered by <img id="logo"></div>
			<span id="real-time" class="ui-li-count"> <!-- <span id="Y"></span> -->
				<span id="MH"></span> <span id="TD"></span> <span id="D"></span> <span
				id="H"></span>: <span id="M"></span>: <span id="S"></span>
			</span>
		</div>
	</div>
</body>
