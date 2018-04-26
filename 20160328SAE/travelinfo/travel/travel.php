<?php
include ("../php/log4all.php");
?>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
<title>实时信息</title>
<link rel="stylesheet" href="../plugins/jquery.mobile.css">
<link rel="stylesheet" href="css/travel.css">
<script src="../plugins/jquery.js"></script>
<script src="../plugins/jquery.mobile.js"></script>
<script type="text/javascript" src="js/is_mobile.js"></script>
<script src="js/travel.js"></script>
</head>
<body>
	<!-- 查询界面 -->
	<div data-role="page" id="searchPage">
		<div data-role="header" data-position="fixed">
			<h1>仅供内部测试</h1>
		</div>
		<div data-role="content">
			<ul data-role="listview">
				<li data-role="list-divider">实时信息</li>
				<li><input type="search" id="busLine" placeholder="输入线路" />
					<ul id="auto" data-role="listview">
						<li class="invisible"></li>
					</ul></li>
			</ul>
			<!-- <a href="#searchResult" data-role="button" data-rel="dialog"
				data-transition="pop" id="searchLine">检索</a> -->
			<a data-role="button" id="searchLine" data-icon="search"
				data-iconpos="right">检索</a> <a data-role="button"
				href="javascript:alert('该功能暂未开放...');//travel_web.html"
				id="searchCustomized" data-icon="search" data-iconpos="right">私人定制</a>
			<div id="lineResult">
				<div class="ui-grid-a">
					<div class="ui-block-a"></div>
					<div class="ui-block-b"></div>
				</div>
			</div>
		</div>
		<!-- 隐藏的线路查询结果模版 start -->
		<div id="lineResultModel" class="invisible">
			<a href="#stationResult" data-role="button" data-inline="true"
				data-rel="dialog" onclick="findByBusKey(this)" class="search-button"
				data-icon="grid">实时信息</a>
			<ul data-role="listview">
				<li><span>路线:</span> <span id="name"></span></li>
				<li><span>开往:</span><span id="direction"></span></li>
				<!-- <li><span>区间:</span> <span id="description"></span></li> -->
				<li><span>开始:</span> <span id="stime"></span></li>
				<li><span>结束:</span><span id="etime"></span></li>
				<li><span>总站数:</span> <span id="count"></span></li>
				<li><span id="bus_id" hidden="true"></span></li>
			</ul>
		</div>
		<!-- 隐藏的线路查询结果模版 end -->
		<div data-role="footer" data-position="fixed">
			<h1>仅供内部测试</h1>
		</div>
	</div>
	<!-- 详细界面 -->
	<div data-role="page" id="stationResult">
		<div data-role="header">
			<h1>查询结果</h1>
		</div>
		<div data-role="content">
			<div class="control-class">
				<a data-role="button" onclick="refreshLineDetail()"
					data-icon="refresh" data-iconpos="notext"></a> <a
					data-role="button" onclick="setHeight()" data-icon="arrow-d"
					data-iconpos="notext"></a> <a data-role="button" onclick="toTop()"
					data-icon="arrow-u" data-iconpos="notext"></a>
			</div>
			<span id="bus_id_in_result" class="invisible"></span>
			<div class="ui-grid-a">
				<ul data-role="listview" id="station_list">
					<li data-role="list-divider">详细信息</li>
					<li class="invisible"><span id="SName"></span><span
						class="float-right"><span id="InTime"></span><span
							id="BusInfo"></span></span></li>
					<li><span>目的地:</span> <span id="LDirection"></span></li>
					<li><span>运行时间:</span><span id="FETime"></span></li>
					<li><span>路线:</span> <span id="lName"></span></li>
					<li data-role="list-divider">站台</li>
				</ul>
			</div>
			<a href="#searchPage" data-inline="true" data-role="button"
				data-icon="back">返回</a>
		</div>
		<div data-role="footer">
			<h1>仅供测试</h1>
		</div>
	</div>
	<div class="loading">
		<img src="img/loading.gif">
	</div>
</body>
</html>