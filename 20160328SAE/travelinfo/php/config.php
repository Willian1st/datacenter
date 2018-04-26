<?php
header('Content-Type:text/html; charset=utf-8');
//不区分大小写 
if (strpos($_SERVER['SERVER_NAME'], "travelinfo.sinaapp.com") !== false) {
	require 'sae_config.php';

} else {
	define('DB_HOST', 'localhost');
	define('DB_USER', 'root');
	define('DB_PWD', 'toor');
	define('DB_NAME', 'sh_user');

	$conn = @ mysql_connect(DB_HOST, DB_USER, DB_PWD) or die('数据库链接失败：' . mysql_error());

	@ mysql_select_db(DB_NAME) or die('数据库错误：' . mysql_error());

	@ mysql_query('SET NAMES UTF8') or die('字符集错误：' . mysql_error());
}
?>