<?php
header("Content-Type: text/html; charset=utf-8");

$gifo = new get_gust_info();
$ip = $gifo->Getip();
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
include ("config.php"); //引入链接数据库 
echo $sql = "insert into log(ip,time,type,site,os,address) value ('$ip','$time','$broswer','$host','$os','$address')";
mysql_query($sql);
echo "插入成功";

class get_gust_info {

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
		echo $ip;
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
?> 

<p id="demo">点击按钮获取您当前坐标（可能需要比较长的时间获取）：</p>
<button onclick="getLocation()">点我</button>
<script>
var x=document.getElementById("demo");
function getLocation()
  {
  if (navigator.geolocation)
    {
    navigator.geolocation.getCurrentPosition(showPosition);
    }
  else{x.innerHTML="该浏览器不支持获取地理位置。";}
  }
function showPosition(position)
  {
  x.innerHTML="纬度: " + position.coords.latitude + 
  "<br>经度: " + position.coords.longitude;	
  }
</script>