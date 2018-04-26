<?php
	header('Content-Type:text/html; charset=utf-8');
	
	$conn =  mysql_connect(SAE_MYSQL_HOST_M.':'.SAE_MYSQL_PORT,SAE_MYSQL_USER,SAE_MYSQL_PASS) or die('数据库链接失败：'.mysql_error());
	
	@mysql_select_db(SAE_MYSQL_DB) or die('数据库错误：'.mysql_error());
	
	@mysql_query('SET NAMES UTF8') or die('字符集错误：'.mysql_error());
?>