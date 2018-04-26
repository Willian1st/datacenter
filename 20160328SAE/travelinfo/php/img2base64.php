<?php
$path = "upload/"; //上传路径  
if (!file_exists($path)) {
	//检查是否有该文件夹，如果没有就创建，并给予最高权限  
	mkdir("$path", 0700);
}
//允许上传的文件格式  
$tp = array (
	"image/gif",
	"image/jpeg",
	"image/png"
);
$result = '';
if (isset ($_POST['upload'])) {
	//检查上传文件是否在允许上传的类型  
	echo $_FILES["filename"]."---------";
	if (!in_array($_FILES["filename"]["type"], $tp)) {
		$result = "上传文件格式不对！";
	}
	if (!is_uploaded_file($_FILES["filename"]["tmp_name"])) { //判断上传文件是否存在
		$result = "文件不存在！";
	}
	$max_size = '500000'; // 最大文件限制（单位：byte）
	if ($_FILES["filename"]["size"] > $max_size) { //判断文件大小是否大于500000字节
		$result = "上传文件太大！";
	}
	if ($result == '') {
		if ($_FILES["filename"]["name"]) {
			$file1 = $_FILES["filename"]["name"];
			//服务器保存地址
			$file2 = $path . time() . $file1;
			$flag = 1;
		}
		if ($flag) {
			$_FILES["filename"]["tmp_name"];
			$result = move_uploaded_file($_FILES["filename"]["tmp_name"], iconv('utf-8', 'gb2312', $file2));
		}
	}
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
	function onClick(temp){
		$(temp).select();
	};
	
</script>
<title>Base64</title>
</head>
<body>
	<div data-role="page">
		<div data-role="header" data-position="fixed">
			<h1>Base64</h1>
		</div>
	<form enctype="multipart/form-data" action="<?php $_SERVER['PHP_SELF']?>" method="post" id="form">
		<div data-role="fieldcontain">
			<input type="file" name="filename" id="filename">
		</div>
		<input id="upload" type="submit" name="upload"   type="button" value="确定上传">
		<ul id="list" data-role="listview" data-inset="true">
		<li data-role="list-divider">真实地址:</li>
		<li><input readonly="true" onclick="onClick(this)" name="after" id="after" value="<?php if(isset($result)){echo $result;} ?>"/></li>
		</ul>
    </form>
		<div data-role="footer" data-position="fixed">
			<h1>Base64转换</h1>
		</div>
	</div>
</body>
</html>