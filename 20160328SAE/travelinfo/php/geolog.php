<?php
header("Content-Type: text/html; charset=utf-8");
$ip = getenv("REMOTE_ADDR");
$time = date('Y-m-d-l H:i:s', $_SERVER['REQUEST_TIME']);
echo $site = "http://" . $_SERVER["HTTP_HOST"] . (($_SERVER["SERVER_PORT"] === "80") ? "" : $_SERVER["SERVER_PORT"]) . $_SERVER["REQUEST_URI"];
$geo = $_GET['geo'];
include ("config.php"); //引入链接数据库 
echo $sql = "insert into geolog(ip,time,site,geo) value ('$ip','$time','$site','$geo')";
mysql_query($sql);
?> 