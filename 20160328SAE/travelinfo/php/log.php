<?php
header("Content-Type: text/html; charset=utf-8");

class get_gustInfo {
	////获得访客浏览器类型
	function GetBrowser() {
		if (!empty ($_SERVER['HTTP_USER_AGENT'])) {
			$br = $_SERVER['HTTP_USER_AGENT'];
			if (preg_match('/MSIE/i', $br)) {
				$br = 'MSIE';
			}
			elseif (preg_match('/Firefox/i', $br)) {
				$br = 'Firefox';
			}
			elseif (preg_match('/Chrome/i', $br)) {
				$br = 'Chrome';
			}
			elseif (preg_match('/Safari/i', $br)) {
				$br = 'Safari';
			}
			elseif (preg_match('/Opera/i', $br)) {
				$br = 'Opera';
			} else {
				$br = 'Other';
			}
			return $br;
		} else {
			return "获取浏览器信息失败！";
		}
	}

	////获得访客浏览器语言
	function GetLang() {
		if (!empty ($_SERVER['HTTP_ACCEPT_LANGUAGE'])) {
			$lang = $_SERVER['HTTP_ACCEPT_LANGUAGE'];
			$lang = substr($lang, 0, 5);
			if (preg_match("/zh-cn/i", $lang)) {
				$lang = "简体中文";
			}
			elseif (preg_match("/zh/i", $lang)) {
				$lang = "繁体中文";
			} else {
				$lang = "Other";
			}
			return $lang;

		} else {
			return "获取浏览器语言失败！";
		}
	}

	////获取访客操作系统
	function GetOs() {
		if (!empty ($_SERVER['HTTP_USER_AGENT'])) {
			$OS = $_SERVER['HTTP_USER_AGENT'];
			if (preg_match('/win/i', $OS)) {
				$OS = 'Windows';
			}
			elseif (preg_match('/Android/i', $OS)) {
				$OS = 'Android';
			}
			elseif (preg_match('/iPhone/i', $OS)) {
				$OS = 'iPhone';
			}
			elseif (preg_match('/iPad/i', $OS)) {
				$OS = 'iPad';
			}
			elseif (preg_match('/ios/i', $OS)) {
				$OS = 'iOS';
			}
			elseif (preg_match('/mac/i', $OS)) {
				$OS = 'MAC';
			}
			elseif (preg_match('/linux/i', $OS)) {
				$OS = 'Linux';
			}
			elseif (preg_match('/unix/i', $OS)) {
				$OS = 'Unix';
			}
			elseif (preg_match('/bsd/i', $OS)) {
				$OS = 'BSD';
			} else {
				$OS = 'Other';
			}
			return $OS;
		} else {
			return "获取访客操作系统信息失败！";
		}
	}

	////获得访客真实ip
	function Getip() {
		global $ip;
		if (getenv("HTTP_CLIENT_IP"))
			$ip = getenv("HTTP_CLIENT_IP");
		else
			if (getenv("HTTP_X_FORWARDED_FOR"))
				$ip = getenv("HTTP_X_FORWARDED_FOR");
			else
				if (getenv("REMOTE_ADDR"))
					$ip = getenv("REMOTE_ADDR");
				else
					$ip = "Unknow";
		return $ip;
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
$gifo = new get_gustInfo();
$ip = getenv("REMOTE_ADDR");
$ipadds = $gifo->Getaddress($ip);
$address = '';
foreach ($ipadds[0] as $value) {
	$address = $address . $value . " ";
}
$broswer = $gifo->GetBrowser();
$lang = $gifo->GetLang();
$os = $gifo->GetOs();
$agent = $_SERVER['HTTP_USER_AGENT'];
$host = $_SERVER['HTTP_HOST'];
$file = $_SERVER['SCRIPT_FILENAME'];
$admin = $_SERVER['SERVER_ADMIN'];
$method = $_SERVER['REQUEST_METHOD'];
$uri = $_SERVER['REQUEST_URI'];
$time = date('Y-m-d-l H:i:s', $_SERVER['REQUEST_TIME']);
$comspec = $_SERVER['COMSPEC'];
$software = $_SERVER['SERVER_SOFTWARE'];
$server_addr = $_SERVER['SERVER_ADDR'];
$server_port = $_SERVER['SERVER_PORT'];
include_once ("config.php"); //引入链接数据库 
$sql = "insert into log(ip,time,type,site,os,address,user_agent) value ('$ip','$time','$broswer','$host','$os','$address','$agent')";
mysql_query($sql);
?> 