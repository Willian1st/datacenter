<?php


//引入链接数据库 
require 'config.php';

$page = $_POST['page'];
$pageSize = $_POST['rows'];
$first = $pageSize * ($page -1);
$order = $_POST['order'];
$sort = $_POST['sort'];
$query = mysql_query("SELECT * FROM log ORDER BY $sort $order LIMIT $first,$pageSize") or die('SQL 错误！');
$json = '';

while (!!$row = mysql_fetch_array($query, MYSQL_ASSOC)) {
	$json .= json_encode($row) . ',';
}

$total = mysql_num_rows(mysql_query("SELECT * FROM log"));

$json = substr($json, 0, -1);
echo '{"total":' . $total . ', "rows":[' . $json . ']}';

mysql_close();
?>