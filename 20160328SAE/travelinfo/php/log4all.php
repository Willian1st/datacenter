<?php
header("Content-Type: text/html; charset=utf-8");

class get_gust_info {
	////获得访客浏览器类型
	function GetBrowser() {
		if (!empty ($_SERVER['HTTP_USER_AGENT'])) {

			$br = $_SERVER['HTTP_USER_AGENT'];
			$result = $br;
			if (preg_match('/MSIE/i', $br)) {
				$result = 'MSIE';
			}
			elseif (preg_match('/Firefox/i', $br)) {
				$result = 'Firefox';
			}
			elseif (preg_match('/Chrome/i', $br)) {
				$result = 'Chrome';
			}
			elseif (preg_match('/Safari/i', $br)) {
				$result = 'Safari';
			}
			elseif (preg_match('/Opera/i', $br)) {
				$result = 'Opera';
			} else {
				$result = 'Other';
			}

			if (preg_match('/MicroMessenger/i', $br)) {
				$result = 'MicroMessenger';
			}
			elseif (preg_match('/UCBrowser/i', $br)) {
				$result = 'UCBrowser';
			}
			elseif (preg_match('/MiuiBrowser/i', $br)) {
				$result = 'MiuiBrowser';
			}
			elseif (preg_match('/TXMicroBlog/i', $br)) {
				$result = 'TXMicroBlog';
			}
			elseif (preg_match('/Weibo/i', $br)) {
				$result = 'Weibo';
			}
			elseif (preg_match('/QQ/i', $br)) {
				$result = 'QQ';
			}
			if (preg_match('/IPadQQ/i', $br)) {
				$result = 'IPadQQ';
			}
			elseif (preg_match('/MQQBrowser/i', $br)) {
				$result = 'MQQBrowser';
			}
			elseif (preg_match('/Qzone/i', $br)) {
				$result = 'Qzone';
			}

			return $result;
		} else {
			return "获取浏览器信息失败！";
		}
	}

	////获取访客操作系统
	function GetOs() {
		if (!empty ($_SERVER['HTTP_USER_AGENT'])) {
			$OS = $_SERVER['HTTP_USER_AGENT'];
			$result = $OS;
			if (preg_match('/win/i', $OS)) {
				$result = 'Windows';
			}
			elseif (preg_match('/Android/i', $OS)) {
				$result = 'Android';
			}
			elseif (preg_match('/Mac/i', $OS)) {
				$result = 'Mac';
			}
			elseif (preg_match('/linux/i', $OS)) {
				$result = 'Linux';
			}
			elseif (preg_match('/unix/i', $OS)) {
				$result = 'Unix';
			}
			elseif (preg_match('/bsd/i', $OS)) {
				$result = 'BSD';
			} else {
				$result = 'Other';
			}

			if (preg_match('/MI 2S/i', $OS)) {
				$result = 'MI 2S';
			}
			elseif (preg_match('/MI 2/i', $OS)) {
				$result = 'MI 2';
			}
			elseif (preg_match('/HM 1SC/i', $OS)) {
				$result = 'HM 1SC';
			}
			elseif (preg_match('/MI 4LTE/i', $OS)) {
				$result = 'MI 4LTE';
			}
			elseif (preg_match('/MI 4C/i', $OS)) {
				$result = 'MI 4C';
			}
			elseif (preg_match('/MI 4/i', $OS)) {
				$result = 'MI 4';
			}
			elseif (preg_match('/HM NOTE/i', $OS)) {
				$result = 'HM NOTE';
			}
			elseif (preg_match('/SM-G7108/i', $OS)) {
				$result = 'SM-G7108';
			}
			elseif (preg_match('/GT-I9300/i', $OS)) {
				$result = 'GT-I9300';
			}
			elseif (preg_match('/GT-I9268/i', $OS)) {
				$result = 'GT-I9268';
			}
			elseif (preg_match('/HONOR H30-L02/i', $OS)) {
				$result = 'HONOR H30-L02';
			}
			elseif (preg_match('/HuaweiT8830/i', $OS)) {
				$result = 'HuaweiT8830';
			}
			elseif (preg_match('/HUAWEI T8950/i', $OS)) {
				$result = 'HUAWEI T8950';
			}
			elseif (preg_match('/LT26i/i', $OS)) {
				$result = 'LT26i';
			}
			elseif (preg_match('/Z10/i', $OS)) {
				$result = 'Z10';
			}
			elseif (preg_match('/M032/i', $OS)) {
				$result = 'M032';
			}
			elseif (preg_match('/iPhone/i', $OS)) {
				$result = 'iPhone';
			}
			elseif (preg_match('/iPad/i', $OS)) {
				$result = 'iPad';
			}
			elseif (preg_match('/Tablet PC/i', $OS)) {
				$result = 'Tablet PC';
			}
			elseif (preg_match('/HTC/i', $OS)) {
				$result = 'HTC';
			}

			if (preg_match('/Nokia 920/i', $OS)) {
				$result = 'Nokia 920';
			}
			elseif (preg_match('/Nokia/i', $OS)) {
				$result = 'Nokia';
			}
			return $result;
		} else {
			return "获取访客操作系统信息失败！";
		}
	}

	////根据ip获得访客所在地地名
	function Getaddress($ip) {
		$ipadd = file_get_contents("http://int.dpool.sina.com.cn/iplookup/iplookup.php?ip=" . $ip); //根据新浪api接口获取
		if ($ipadd) {
			$charset = iconv("gbk", "utf-8", $ipadd);
			preg_match_all("/[\x{4e00}-\x{9fa5}]+/u", $charset, $ipadds);

			return $ipadds; //返回一个二维数组
		} else {
			return "-";
		}
	}
}
$gifo = new get_gust_info();
$ip = getenv("REMOTE_ADDR");
$ipadds = $gifo->Getaddress($ip);
$address = '';
foreach ($ipadds[0] as $value) {
	$address = $address . $value . " ";
}
$broswer = $gifo->GetBrowser();
$os = $gifo->GetOs();
$agent = $_SERVER['HTTP_USER_AGENT'];
$host = 'http://' . $_SERVER['SERVER_NAME'] . ':' . $_SERVER["SERVER_PORT"] . $_SERVER["REQUEST_URI"];
$time = date('Y-m-d H:i:s', $_SERVER['REQUEST_TIME']);
$type = $broswer . " -" . $os;
include_once ("config.php"); //引入链接数据库 
$sql = "insert into log4all(ip,address,time,type,site,user_agent,flag) value ('$ip','$address','$time','$type','$host','$agent',0)";
mysql_query($sql);
?> 