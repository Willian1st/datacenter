<?php
if (isset ($_POST['convert'])) {
	$url = $_POST['before'];
	$url = str_ireplace('thunder://', '', $url);
	$url = str_ireplace('qqdl://', '', $url);
	$url = str_ireplace('flashget://', '', $url);
	$url = base64_decode($url);
	$url = str_ireplace('[FLASHGET]', '', $url);
	if (preg_match('/AA(.*)ZZ/', $url, $result)) {
		$result = $result[1];
	} else {
		$result = $url;
	}
	echo '{"result"' . $result . '}';
}
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
<script type="text/javascript" src="../travel/js/is_mobile.js"></script>
<script type="text/javascript">
	function check(temp){
		var value=$(temp).find("#before").val();
		if(value==""){
			alert("请输入要转换的地址...");
			return false;
		}
		if(value.indexOf("thunder://")>=0||value.indexOf("qqdl://")>=0||value.indexOf("flashget://")>=0||value.indexOf("[FLASHGET]")>=0){
			return true;
		}else{
			alert("输入的地址未加密或无法识别...");
			return false;
		}
	};
	function onClick(temp){
		$(temp).select();
	};
	
</script>
<title>下载地址解析</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>下载地址解析</h1>
		</div>
		<form action="converter.php" method="post" id="form"  onsubmit="return check(this)">
	
		<div data-role="fieldcontain">
			<input type="search" name="before" id="before" value="<?php if(isset($_POST['before'])){echo $_POST['before'];} ?>"
				placeholder="地址类型包括:迅雷,快车,旋风...">
		</div>
		<input id="convert" type="submit" name="convert"   type="button" value="转换">
		<ul id="list" data-role="listview" data-inset="true">
		<li data-role="list-divider">真实地址:</li>
		<li><input readonly="true" onclick="onClick(this)" name="after" id="after" value="<?php if(isset($result)){echo $result;} ?>"/></li>
		</ul>
		
</form>
		<div data-role="footer" data-position="fixed">
			<h1>下载地址解析</h1>
		</div>
	</div>
</body>
</html>