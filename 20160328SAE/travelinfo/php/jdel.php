<?php


//引入链接数据库 
require 'config.php';
$ids = $_POST["ids"];
$query = mysql_query("update log4all set flag='1' where id in ($ids)") or die('SQL 错误！');
echo '{"total":' . mysql_affected_rows(). '}';
mysql_close();
?>