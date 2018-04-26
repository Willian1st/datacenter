<?php
header('Content-Type:text/html; charset=utf-8');
/**
*翻译功能
 */

$obj = new callbackapiTest();
$obj->responseMsg();

class callbackapiTest {

	public function responseMsg() {
		//原始的 POST 数据
		$keyword = $_GET['word'];
		$time = time();
		$msgType = "text";
		$str_valid = trim($keyword);
		$contentStr = '';
		if (!empty ($str_valid)) {
			//调用有道词典
			$contentStr = $this->youdaoDic($str_valid) . "【有道翻译】<br>";
			//调用百度词典
			$contentStr = $contentStr . $this->baiduDic($str_valid) . "【百度翻译】";

		} else {
			$contentStr = "请输入要翻译的内容...";
		}
		//把格式化的字符串写入$resultStr
		//$resultStr = sprintf($time, $contentStr);
		$resultStr = $contentStr;
		echo $resultStr;

	}

	public function youdaoDic($str_valid) {

		$keyfrom = "zhuojin"; //申请APIKEY时所填表的网站名称的内容
		$apikey = "304804921"; //从有道申请的APIKEY

		/*/有道翻译-xml格式
		$url_youdao = 'http://fanyi.youdao.com/fanyiapi.do?keyfrom='.$keyfrom.'&key='.$apikey.'&type=data&doctype=xml&version=1.1&q='.$str_valid;
		
		$xmlStyle = simplexml_load_file($url_youdao);
		
		$errorCode = $xmlStyle->errorCode;
		
		$paras = $xmlStyle->translation->paragraph;
		
		if($errorCode == 0){
			return $paras;
		}else{
			return "无法进行有效的翻译";
		}
		*/

		//有道翻译-json格式
		$url_youdao = 'http://fanyi.youdao.com/fanyiapi.do?keyfrom=' . $keyfrom . '&key=' . $apikey . '&type=data&doctype=json&version=1.1&q=' . $str_valid;

		$jsonStyle = file_get_contents($url_youdao);

		$result = json_decode($jsonStyle, true);

		$errorCode = $result['errorCode'];

		$trans = '';

		if (isset ($errorCode)) {

			switch ($errorCode) {
				case 0 :
					$trans = $result['translation']['0'];
					break;
				case 20 :
					$trans = '要翻译的文本过长';
					break;
				case 30 :
					$trans = '无法进行有效的翻译';
					break;
				case 40 :
					$trans = '不支持的语言类型';
					break;
				case 50 :
					$trans = '无效的key';
					break;
				default :
					$trans = '出现异常';
					break;
			}
		}
		return $trans;

	}

	//百度翻译
	public function baiduDic($word, $from = "auto", $to = "auto") {

		//首先对要翻译的文字进行 urlencode 处理
		$word_code = urlencode($word);

		//注册的API Key
		$appid = "Ccm3jPeUHdCnnsqNb8P2nDbe";

		//生成翻译API的URL GET地址
		$baidu_url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=" . $appid . "&q=" . $word_code . "&from=" . $from . "&to=" . $to;
		/*echo $text = json_decode($this->language_text($baidu_url));
		echo $text[0];
		echo $text->trans_result;*/
		$result = json_decode($this->language_text($baidu_url), true);
		$result = $result["trans_result"];
		$result = $result[0]["dst"];
		return $result;
	}

	//百度翻译-获取目标URL所打印的内容
	public function language_text($url) {

		if (!function_exists('file_get_contents')) {

			$file_contents = file_get_contents($url);

		} else {

			//初始化一个cURL对象
			$ch = curl_init();

			$timeout = 5;

			//设置需要抓取的URL
			curl_setopt($ch, CURLOPT_URL, $url);

			//设置cURL 参数，要求结果保存到字符串中还是输出到屏幕上
			curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);

			//在发起连接前等待的时间，如果设置为0，则无限等待
			curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, $timeout);

			//运行cURL，请求网页
			$file_contents = curl_exec($ch);

			//关闭URL请求
			curl_close($ch);
		}

		return $file_contents;
	}
	function __toString() {
		return "";
	}

}
?>