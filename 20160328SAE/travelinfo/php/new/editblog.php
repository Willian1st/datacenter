<?php
//引入链接数据库 
$title = $_POST['title'];
$class = $_POST['class'];
$content = $_POST['content'];
$ip = getenv("REMOTE_ADDR");
//$time = date('Y-m-d-l H:i:s', $_SERVER['REQUEST_TIME']);
$time = date('Y-m-d H:i:s', $_SERVER['REQUEST_TIME']);
$agent = $_SERVER['HTTP_USER_AGENT'];
include ("../config.php"); //引入链接数据库 
$sql = "insert into mblog(title,class,content,ip,addtime,changetime,agent) value ('$title','$class','$content','$ip','$time','$time','$agent')";
echo mysql_query($sql);
?>