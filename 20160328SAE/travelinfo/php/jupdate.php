<?php


//引入链接数据库 
require 'config.php';
$id = $_POST["id"];
$type = $_POST["type"];
$query = mysql_query("update log4all set type='$type' where id = $id") or die('SQL 错误！');
echo '{"total":' . mysql_affected_rows() . '}';
mysql_close();
?>