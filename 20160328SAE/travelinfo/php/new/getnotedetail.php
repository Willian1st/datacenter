<?php


//引入链接数据库 
require '../config.php';

$noteid = $_POST['id'];
$json = '';
$query = mysql_query("SELECT * FROM mblog where id = $noteid ") or die('SQL 错误！');
while (!!$row = mysql_fetch_array($query, MYSQL_ASSOC)) {
	$json .= json_encode($row) . ',';
}
$json = substr($json, 0, -1);
echo $json;
mysql_close();
?>