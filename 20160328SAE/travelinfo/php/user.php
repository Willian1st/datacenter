<?php
echo $_SERVER['HTTP_USER_AGENT'];

//引入链接数据库 
require 'config.php';

$page = $_GET['page'];
$pageSize = $_GET['rows'];
$first = $pageSize * ($page -1);

$query = mysql_query("SELECT * FROM log LIMIT $first,$pageSize") or die('SQL 错误！');
$json = '';

while (!!$row = mysql_fetch_array($query, MYSQL_ASSOC)) {
	$json .= json_encode($row) . ',';
}

$total = mysql_num_rows(mysql_query("SELECT * FROM t_user LIMIT $first,$pageSize"));

$json = substr($json, 0, -1);
echo '{"total":' . $total . ', "rows":[' . $json . ']}';

mysql_close();
?>