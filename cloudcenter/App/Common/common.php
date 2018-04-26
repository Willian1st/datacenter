<?php
function show_db_errorxx() {
	exit ('系统访问量大，请稍等添加数据');
}
function logHistory() {
	$agent = $_SERVER['HTTP_USER_AGENT'];
	$ip = get_client_ip();
	$host = $_SERVER['SERVER_NAME'] . ':' . $_SERVER["SERVER_PORT"] . $_SERVER["REQUEST_URI"];
	$model = M('loghistory');
	$data['visittime'] = time();
	$data['agent'] = $agent;
	$data['ip'] = $ip;
	$data['host'] = $host;
	$address = '';
	$addr = getaddress($ip);
	foreach ($addr[0] as $value) {
		$address = $address . $value . " ";
	}
	$data['address'] = ($address == "" ? "未知" : $address);
	$model->add($data);
}
//根据ip获得访客所在地地名
function getaddress($ip) {
	$ipadd = file_get_contents("http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=" . $ip); //根据新浪api接口获取
	if ($ipadd) {
		$charset = iconv("gbk", "utf-8", $ipadd);
		preg_match_all("/[\x{4e00}-\x{9fa5}]+/u", $charset, $ipadds);

		return $ipadds; //返回一个二维数组
	} else {
		return "-";
	}
}
?>