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
<script type="text/javascript" src="js/color.js"></script>
<style type="text/css">
body {
	font-size: 12px;
}

.notranslate {
	width: 100% !important;
	margin: 0 auto;
}

tr td {
	font-size: 12px;
}

tr th {
	font-weight: normal !important;
	color: #FFF;
	background-color: #000000;
}

tr td a {
	font-weight: normal !important;
	text-decoration: none;
	color: #696969 !important;
	text-decoration: none;
}
</style>
<title>颜色拾取</title>
</head>
<body>
	<div data-role="page" id="colorPage">
		<div data-role="header" data-position="fixed">
			<h1>颜色查询</h1>
		</div>
		<div id="content">
			<p>目前所有浏览器都支持以下颜色名。</p>
			<p>141个颜色名称是在HTML和CSS颜色规范定义的（17标准颜色，再加124）。下表列出了所有颜色的值，包括十六进制值。</p>
			<hr>
			<table class="reference notranslate">
				<tbody>
					<tr>
						<th align="left" width="30%">颜色名</th>
						<th align="left" width="30%">16进制</th>
						<th align="left" width="40%">颜色</th>
					</tr>
					<tr>
						<td align="left"><a href="AliceBlue">AliceBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F0F8FF</a></td>
						<td bgcolor="#F0F8FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="AntiqueWhite">AntiqueWhite</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FAEBD7</a></td>
						<td bgcolor="#FAEBD7"></td>
					</tr>
					<tr>
						<td align="left"><a href="Aqua">Aqua</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00FFFF</a></td>
						<td bgcolor="#00FFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="Aquamarine">Aquamarine</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#7FFFD4</a></td>
						<td bgcolor="#7FFFD4"></td>
					</tr>
					<tr>
						<td align="left"><a href="Azure">Azure</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F0FFFF</a></td>
						<td bgcolor="#F0FFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="Beige">Beige</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F5F5DC</a></td>
						<td bgcolor="#F5F5DC"></td>
					</tr>
					<tr>
						<td align="left"><a href="Bisque">Bisque</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFE4C4</a></td>
						<td bgcolor="#FFE4C4"></td>
					</tr>
					<tr>
						<td align="left"><a href="Black">Black</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#000000</a></td>
						<td bgcolor="#000000"></td>
					</tr>
					<tr>
						<td align="left"><a href="BlanchedAlmond">BlanchedAlmond</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFEBCD</a></td>
						<td bgcolor="#FFEBCD"></td>
					</tr>
					<tr>
						<td align="left"><a href="Blue">Blue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#0000FF</a></td>
						<td bgcolor="#0000FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="BlueViolet">BlueViolet</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#8A2BE2</a></td>
						<td bgcolor="#8A2BE2"></td>
					</tr>
					<tr>
						<td align="left"><a href="Brown">Brown</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#A52A2A</a></td>
						<td bgcolor="#A52A2A"></td>
					</tr>
					<tr>
						<td align="left"><a href="BurlyWood">BurlyWood</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DEB887</a></td>
						<td bgcolor="#DEB887"></td>
					</tr>
					<tr>
						<td align="left"><a href="CadetBlue">CadetBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#5F9EA0</a></td>
						<td bgcolor="#5F9EA0"></td>
					</tr>
					<tr>
						<td align="left"><a href="Chartreuse">Chartreuse</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#7FFF00</a></td>
						<td bgcolor="#7FFF00"></td>
					</tr>
					<tr>
						<td align="left"><a href="Chocolate">Chocolate</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#D2691E</a></td>
						<td bgcolor="#D2691E"></td>
					</tr>
					<tr>
						<td align="left"><a href="Coral">Coral</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF7F50</a></td>
						<td bgcolor="#FF7F50"></td>
					</tr>
					<tr>
						<td align="left"><a href="CornflowerBlue">CornflowerBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#6495ED</a></td>
						<td bgcolor="#6495ED"></td>
					</tr>
					<tr>
						<td align="left"><a href="Cornsilk">Cornsilk</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFF8DC</a></td>
						<td bgcolor="#FFF8DC"></td>
					</tr>
					<tr>
						<td align="left"><a href="Crimson">Crimson</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DC143C</a></td>
						<td bgcolor="#DC143C"></td>
					</tr>
					<tr>
						<td align="left"><a href="Cyan">Cyan</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00FFFF</a></td>
						<td bgcolor="#00FFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkBlue">DarkBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00008B</a></td>
						<td bgcolor="#00008B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkCyan">DarkCyan</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#008B8B</a></td>
						<td bgcolor="#008B8B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkGoldenRod">DarkGoldenRod</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#B8860B</a></td>
						<td bgcolor="#B8860B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkGray">DarkGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#A9A9A9</a></td>
						<td bgcolor="#A9A9A9"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkGreen">DarkGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#006400</a></td>
						<td bgcolor="#006400"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkKhaki">DarkKhaki</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#BDB76B</a></td>
						<td bgcolor="#BDB76B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkMagenta">DarkMagenta</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#8B008B</a></td>
						<td bgcolor="#8B008B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkOliveGreen">DarkOliveGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#556B2F</a></td>
						<td bgcolor="#556B2F"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkOrange">DarkOrange</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF8C00</a></td>
						<td bgcolor="#FF8C00"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkOrchid">DarkOrchid</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#9932CC</a></td>
						<td bgcolor="#9932CC"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkRed">DarkRed</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#8B0000</a></td>
						<td bgcolor="#8B0000"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkSalmon">DarkSalmon</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#E9967A</a></td>
						<td bgcolor="#E9967A"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkSeaGreen">DarkSeaGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#8FBC8F</a></td>
						<td bgcolor="#8FBC8F"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkSlateBlue">DarkSlateBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#483D8B</a></td>
						<td bgcolor="#483D8B"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkSlateGray">DarkSlateGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#2F4F4F</a></td>
						<td bgcolor="#2F4F4F"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkTurquoise">DarkTurquoise</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00CED1</a></td>
						<td bgcolor="#00CED1"></td>
					</tr>
					<tr>
						<td align="left"><a href="DarkViolet">DarkViolet</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#9400D3</a></td>
						<td bgcolor="#9400D3"></td>
					</tr>
					<tr>
						<td align="left"><a href="DeepPink">DeepPink</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF1493</a></td>
						<td bgcolor="#FF1493"></td>
					</tr>
					<tr>
						<td align="left"><a href="DeepSkyBlue">DeepSkyBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00BFFF</a></td>
						<td bgcolor="#00BFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="DimGray">DimGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#696969</a></td>
						<td bgcolor="#696969"></td>
					</tr>
					<tr>
						<td align="left"><a href="DodgerBlue">DodgerBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#1E90FF</a></td>
						<td bgcolor="#1E90FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="FireBrick">FireBrick</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#B22222</a></td>
						<td bgcolor="#B22222"></td>
					</tr>
					<tr>
						<td align="left"><a href="FloralWhite">FloralWhite</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFAF0</a></td>
						<td bgcolor="#FFFAF0"></td>
					</tr>
					<tr>
						<td align="left"><a href="ForestGreen">ForestGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#228B22</a></td>
						<td bgcolor="#228B22"></td>
					</tr>
					<tr>
						<td align="left"><a href="Fuchsia">Fuchsia</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF00FF</a></td>
						<td bgcolor="#FF00FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="Gainsboro">Gainsboro</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DCDCDC</a></td>
						<td bgcolor="#DCDCDC"></td>
					</tr>
					<tr>
						<td align="left"><a href="GhostWhite">GhostWhite</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F8F8FF</a></td>
						<td bgcolor="#F8F8FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="Gold">Gold</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFD700</a></td>
						<td bgcolor="#FFD700"></td>
					</tr>
					<tr>
						<td align="left"><a href="GoldenRod">GoldenRod</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DAA520</a></td>
						<td bgcolor="#DAA520"></td>
					</tr>
					<tr>
						<td align="left"><a href="Gray">Gray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#808080</a></td>
						<td bgcolor="#808080"></td>
					</tr>
					<tr>
						<td align="left"><a href="Green">Green</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#008000</a></td>
						<td bgcolor="#008000"></td>
					</tr>
					<tr>
						<td align="left"><a href="GreenYellow">GreenYellow</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#ADFF2F</a></td>
						<td bgcolor="#ADFF2F"></td>
					</tr>
					<tr>
						<td align="left"><a href="HoneyDew">HoneyDew</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F0FFF0</a></td>
						<td bgcolor="#F0FFF0"></td>
					</tr>
					<tr>
						<td align="left"><a href="HotPink">HotPink</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF69B4</a></td>
						<td bgcolor="#FF69B4"></td>
					</tr>
					<tr>
						<td align="left"><a href="IndianRed ">IndianRed </a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#CD5C5C</a></td>
						<td bgcolor="#CD5C5C"></td>
					</tr>
					<tr>
						<td align="left"><a href="Indigo ">Indigo </a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#4B0082</a></td>
						<td bgcolor="#4B0082"></td>
					</tr>
					<tr>
						<td align="left"><a href="Ivory">Ivory</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFFF0</a></td>
						<td bgcolor="#FFFFF0"></td>
					</tr>
					<tr>
						<td align="left"><a href="Khaki">Khaki</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F0E68C</a></td>
						<td bgcolor="#F0E68C"></td>
					</tr>
					<tr>
						<td align="left"><a href="Lavender">Lavender</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#E6E6FA</a></td>
						<td bgcolor="#E6E6FA"></td>
					</tr>
					<tr>
						<td align="left"><a href="LavenderBlush">LavenderBlush</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFF0F5</a></td>
						<td bgcolor="#FFF0F5"></td>
					</tr>
					<tr>
						<td align="left"><a href="LawnGreen">LawnGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#7CFC00</a></td>
						<td bgcolor="#7CFC00"></td>
					</tr>
					<tr>
						<td align="left"><a href="LemonChiffon">LemonChiffon</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFACD</a></td>
						<td bgcolor="#FFFACD"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightBlue">LightBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#ADD8E6</a></td>
						<td bgcolor="#ADD8E6"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightCoral">LightCoral</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F08080</a></td>
						<td bgcolor="#F08080"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightCyan">LightCyan</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#E0FFFF</a></td>
						<td bgcolor="#E0FFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightGoldenRodYellow">LightGoldenRodYellow</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FAFAD2</a></td>
						<td bgcolor="#FAFAD2"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightGray">LightGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#D3D3D3</a></td>
						<td bgcolor="#D3D3D3"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightGreen">LightGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#90EE90</a></td>
						<td bgcolor="#90EE90"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightPink">LightPink</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFB6C1</a></td>
						<td bgcolor="#FFB6C1"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightSalmon">LightSalmon</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFA07A</a></td>
						<td bgcolor="#FFA07A"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightSeaGreen">LightSeaGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#20B2AA</a></td>
						<td bgcolor="#20B2AA"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightSkyBlue">LightSkyBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#87CEFA</a></td>
						<td bgcolor="#87CEFA"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightSlateGray">LightSlateGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#778899</a></td>
						<td bgcolor="#778899"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightSteelBlue">LightSteelBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#B0C4DE</a></td>
						<td bgcolor="#B0C4DE"></td>
					</tr>
					<tr>
						<td align="left"><a href="LightYellow">LightYellow</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFFE0</a></td>
						<td bgcolor="#FFFFE0"></td>
					</tr>
					<tr>
						<td align="left"><a href="Lime">Lime</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00FF00</a></td>
						<td bgcolor="#00FF00"></td>
					</tr>
					<tr>
						<td align="left"><a href="LimeGreen">LimeGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#32CD32</a></td>
						<td bgcolor="#32CD32"></td>
					</tr>
					<tr>
						<td align="left"><a href="Linen">Linen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FAF0E6</a></td>
						<td bgcolor="#FAF0E6"></td>
					</tr>
					<tr>
						<td align="left"><a href="Magenta">Magenta</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF00FF</a></td>
						<td bgcolor="#FF00FF"></td>
					</tr>
					<tr>
						<td align="left"><a href="Maroon">Maroon</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#800000</a></td>
						<td bgcolor="#800000"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumAquaMarine">MediumAquaMarine</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#66CDAA</a></td>
						<td bgcolor="#66CDAA"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumBlue">MediumBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#0000CD</a></td>
						<td bgcolor="#0000CD"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumOrchid">MediumOrchid</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#BA55D3</a></td>
						<td bgcolor="#BA55D3"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumPurple">MediumPurple</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#9370DB</a></td>
						<td bgcolor="#9370DB"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumSeaGreen">MediumSeaGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#3CB371</a></td>
						<td bgcolor="#3CB371"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumSlateBlue">MediumSlateBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#7B68EE</a></td>
						<td bgcolor="#7B68EE"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumSpringGreen">MediumSpringGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00FA9A</a></td>
						<td bgcolor="#00FA9A"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumTurquoise">MediumTurquoise</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#48D1CC</a></td>
						<td bgcolor="#48D1CC"></td>
					</tr>
					<tr>
						<td align="left"><a href="MediumVioletRed">MediumVioletRed</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#C71585</a></td>
						<td bgcolor="#C71585"></td>
					</tr>
					<tr>
						<td align="left"><a href="MidnightBlue">MidnightBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#191970</a></td>
						<td bgcolor="#191970"></td>
					</tr>
					<tr>
						<td align="left"><a href="MintCream">MintCream</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F5FFFA</a></td>
						<td bgcolor="#F5FFFA"></td>
					</tr>
					<tr>
						<td align="left"><a href="MistyRose">MistyRose</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFE4E1</a></td>
						<td bgcolor="#FFE4E1"></td>
					</tr>
					<tr>
						<td align="left"><a href="Moccasin">Moccasin</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFE4B5</a></td>
						<td bgcolor="#FFE4B5"></td>
					</tr>
					<tr>
						<td align="left"><a href="NavajoWhite">NavajoWhite</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFDEAD</a></td>
						<td bgcolor="#FFDEAD"></td>
					</tr>
					<tr>
						<td align="left"><a href="Navy">Navy</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#000080</a></td>
						<td bgcolor="#000080"></td>
					</tr>
					<tr>
						<td align="left"><a href="OldLace">OldLace</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FDF5E6</a></td>
						<td bgcolor="#FDF5E6"></td>
					</tr>
					<tr>
						<td align="left"><a href="Olive">Olive</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#808000</a></td>
						<td bgcolor="#808000"></td>
					</tr>
					<tr>
						<td align="left"><a href="OliveDrab">OliveDrab</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#6B8E23</a></td>
						<td bgcolor="#6B8E23"></td>
					</tr>
					<tr>
						<td align="left"><a href="Orange">Orange</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFA500</a></td>
						<td bgcolor="#FFA500"></td>
					</tr>
					<tr>
						<td align="left"><a href="OrangeRed">OrangeRed</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF4500</a></td>
						<td bgcolor="#FF4500"></td>
					</tr>
					<tr>
						<td align="left"><a href="Orchid">Orchid</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DA70D6</a></td>
						<td bgcolor="#DA70D6"></td>
					</tr>
					<tr>
						<td align="left"><a href="PaleGoldenRod">PaleGoldenRod</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#EEE8AA</a></td>
						<td bgcolor="#EEE8AA"></td>
					</tr>
					<tr>
						<td align="left"><a href="PaleGreen">PaleGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#98FB98</a></td>
						<td bgcolor="#98FB98"></td>
					</tr>
					<tr>
						<td align="left"><a href="PaleTurquoise">PaleTurquoise</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#AFEEEE</a></td>
						<td bgcolor="#AFEEEE"></td>
					</tr>
					<tr>
						<td align="left"><a href="PaleVioletRed">PaleVioletRed</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DB7093</a></td>
						<td bgcolor="#DB7093"></td>
					</tr>
					<tr>
						<td align="left"><a href="PapayaWhip">PapayaWhip</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFEFD5</a></td>
						<td bgcolor="#FFEFD5"></td>
					</tr>
					<tr>
						<td align="left"><a href="PeachPuff">PeachPuff</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFDAB9</a></td>
						<td bgcolor="#FFDAB9"></td>
					</tr>
					<tr>
						<td align="left"><a href="Peru">Peru</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#CD853F</a></td>
						<td bgcolor="#CD853F"></td>
					</tr>
					<tr>
						<td align="left"><a href="Pink">Pink</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFC0CB</a></td>
						<td bgcolor="#FFC0CB"></td>
					</tr>
					<tr>
						<td align="left"><a href="Plum">Plum</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#DDA0DD</a></td>
						<td bgcolor="#DDA0DD"></td>
					</tr>
					<tr>
						<td align="left"><a href="PowderBlue">PowderBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#B0E0E6</a></td>
						<td bgcolor="#B0E0E6"></td>
					</tr>
					<tr>
						<td align="left"><a href="Purple">Purple</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#800080</a></td>
						<td bgcolor="#800080"></td>
					</tr>
					<tr>
						<td align="left"><a href="Red">Red</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF0000</a></td>
						<td bgcolor="#FF0000"></td>
					</tr>
					<tr>
						<td align="left"><a href="RosyBrown">RosyBrown</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#BC8F8F</a></td>
						<td bgcolor="#BC8F8F"></td>
					</tr>
					<tr>
						<td align="left"><a href="RoyalBlue">RoyalBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#4169E1</a></td>
						<td bgcolor="#4169E1"></td>
					</tr>
					<tr>
						<td align="left"><a href="SaddleBrown">SaddleBrown</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#8B4513</a></td>
						<td bgcolor="#8B4513"></td>
					</tr>
					<tr>
						<td align="left"><a href="Salmon">Salmon</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FA8072</a></td>
						<td bgcolor="#FA8072"></td>
					</tr>
					<tr>
						<td align="left"><a href="SandyBrown">SandyBrown</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F4A460</a></td>
						<td bgcolor="#F4A460"></td>
					</tr>
					<tr>
						<td align="left"><a href="SeaGreen">SeaGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#2E8B57</a></td>
						<td bgcolor="#2E8B57"></td>
					</tr>
					<tr>
						<td align="left"><a href="SeaShell">SeaShell</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFF5EE</a></td>
						<td bgcolor="#FFF5EE"></td>
					</tr>
					<tr>
						<td align="left"><a href="Sienna">Sienna</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#A0522D</a></td>
						<td bgcolor="#A0522D"></td>
					</tr>
					<tr>
						<td align="left"><a href="Silver">Silver</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#C0C0C0</a></td>
						<td bgcolor="#C0C0C0"></td>
					</tr>
					<tr>
						<td align="left"><a href="SkyBlue">SkyBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#87CEEB</a></td>
						<td bgcolor="#87CEEB"></td>
					</tr>
					<tr>
						<td align="left"><a href="SlateBlue">SlateBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#6A5ACD</a></td>
						<td bgcolor="#6A5ACD"></td>
					</tr>
					<tr>
						<td align="left"><a href="SlateGray">SlateGray</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#708090</a></td>
						<td bgcolor="#708090"></td>
					</tr>
					<tr>
						<td align="left"><a href="Snow">Snow</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFAFA</a></td>
						<td bgcolor="#FFFAFA"></td>
					</tr>
					<tr>
						<td align="left"><a href="SpringGreen">SpringGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#00FF7F</a></td>
						<td bgcolor="#00FF7F"></td>
					</tr>
					<tr>
						<td align="left"><a href="SteelBlue">SteelBlue</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#4682B4</a></td>
						<td bgcolor="#4682B4"></td>
					</tr>
					<tr>
						<td align="left"><a href="Tan">Tan</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#D2B48C</a></td>
						<td bgcolor="#D2B48C"></td>
					</tr>
					<tr>
						<td align="left"><a href="Teal">Teal</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#008080</a></td>
						<td bgcolor="#008080"></td>
					</tr>
					<tr>
						<td align="left"><a href="Thistle">Thistle</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#D8BFD8</a></td>
						<td bgcolor="#D8BFD8"></td>
					</tr>
					<tr>
						<td align="left"><a href="Tomato">Tomato</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FF6347</a></td>
						<td bgcolor="#FF6347"></td>
					</tr>
					<tr>
						<td align="left"><a href="Turquoise">Turquoise</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#40E0D0</a></td>
						<td bgcolor="#40E0D0"></td>
					</tr>
					<tr>
						<td align="left"><a href="Violet">Violet</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#EE82EE</a></td>
						<td bgcolor="#EE82EE"></td>
					</tr>
					<tr>
						<td align="left"><a href="Wheat">Wheat</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F5DEB3</a></td>
						<td bgcolor="#F5DEB3"></td>
					</tr>
					<tr>
						<td align="left"><a href="White">White</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFFFF</a></td>
						<td bgcolor="#FFFFFF"></td>
					</tr>
					<tr>
						<td align="left"><a href="WhiteSmoke">WhiteSmoke</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#F5F5F5</a></td>
						<td bgcolor="#F5F5F5"></td>
					</tr>
					<tr>
						<td align="left"><a href="Yellow">Yellow</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#FFFF00</a></td>
						<td bgcolor="#FFFF00"></td>
					</tr>
					<tr>
						<td align="left"><a href="YellowGreen">YellowGreen</a></td>
						<td align="left"><a class="color" data-rel="dialog"  href="#dialog">#9ACD32</a></td>
						<td bgcolor="#9ACD32"></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div data-role="footer" data-position="fixed">
			<h1>颜色查询</h1>
		</div>
	</div>
	<div data-role="page" id="dialog">
		<div data-role="header">
			<h1>您选择的颜色:</h1>
		</div>
		<div data-role="content">
			 <div>颜色值:<span  id="colorCode" style="float:right;">335</span></div>
			 <div>颜色:<span  id="colour" style="float:right;">Color</span></div>
			 <p><a href="#colorPage" style="float:right;">返回</a></p>
		</div>
		
		<div data-role="footer">
			<h1>详情界面</h1>
		</div>
	</div>
	 <style type="text/css">
		.ui-dialog-contain{
			margin-top:150px;
		}
	 </style>
</body>
</html>