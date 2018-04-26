<?php
Class UserModel extends Model {
	protected $_map = array (
		'username' => 'username',
		'code' => 'code',
		
	);

	// 定义主键
	protected $pk = 'userid';
}