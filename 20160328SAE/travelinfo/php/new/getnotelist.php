<?php


//引入链接数据库 
require '../config.php';

$page = $_POST['page'];
$pageSize = $_POST['rows'];
$first = $pageSize * ($page -1);
$order = $_POST['order'];
$sort = $_POST['sort'];

$json = '';
//查询条件SQL
$select_sql = '';
$ip = '';
$address = '';
$from = '';
$to = '';
$site = "";
$type = '';
if (isset ($_POST['ip']) && !empty ($_POST['ip'])) {
	$ip = "ip like '%{$_POST['ip']}%' and ";
	$select_sql .= $ip;
}
if (isset ($_POST['address']) && !empty ($_POST['address'])) {
	$address = "address like '%{$_POST['address']}%' and ";
	$select_sql .= $address;
}
if (isset ($_POST['from']) && !empty ($_POST['from'])) {
	$from = "time >= '{$_POST['from']}' and ";
	$select_sql .= $from;
}
if (isset ($_POST['to']) && !empty ($_POST['to'])) {
	$from = "time <= '{$_POST['to']}' and ";
	$select_sql .= $to;
}
if (isset ($_POST['type']) && !empty ($_POST['type'])) {
	$type = "type like '%{$_POST['type']}%' and ";
	$select_sql .= $type;
}
if (isset ($_POST['site']) && !empty ($_POST['site'])) {
	$site = "site like '%{$_POST['site']}%' and ";
	$select_sql .= $site;
}
if (isset ($_POST['user_agent']) && !empty ($_POST['user_agent'])) {
	$user_agent = "user_agent like '%{$_POST['user_agent']}%' and ";
	$select_sql .= $user_agent;
}
/*if ($select_sql != '') {
	$select_sql = 'where flag !=1 and ' . substr($select_sql, 0, -4);
}else{
	$select_sql = 'where flag !=1 ';
}*/
echo $select_sql;
$query = mysql_query("SELECT * FROM mblog $select_sql ORDER BY $sort $order LIMIT $first,$pageSize") or die('SQL 错误！');
while (!!$row = mysql_fetch_array($query, MYSQL_ASSOC)) {
	$json .= json_encode($row) . ',';
}

$total = mysql_num_rows(mysql_query("SELECT * FROM log4all $select_sql"));

$json = substr($json, 0, -1);
echo '{"total":' . $total . ', "rows":[' . $json . ']}';

mysql_close();
?>