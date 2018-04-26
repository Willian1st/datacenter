<?php


//注意，请不要在这里配置SAE的数据库，配置你本地的数据库就可以了。
return array (
	//'配置项'=>'配置值'
	'APP_GROUP_LIST'=>'Index,Admin',
	'DEFAULT_GROUP'=>'Index',
	'SHOW_PAGE_TRACE' => false,//是否显示调试信息
	
	'URL_HTML_SUFFIX' => '.html',
	//数据库配置
	'DB_TYPE' => 'mysql', // 数据库类型
	'DB_HOST' => '127.0.0.1', // 服务器地址
	'DB_NAME' => 'datacenter', // 数据库名
	'DB_USER' => 'root', // 用户名
	'DB_PWD' => '', // 密码
	'DB_PORT' => '3306', // 端口
	'DB_PREFIX' => 'sae_', // 数据库表前缀 
	'DB_CHARSET' => 'utf8', // 字符集
	'DB_DEBUG' => true, // 数据库调试模式 开启后可以记录SQL日志 */

	
);
?>