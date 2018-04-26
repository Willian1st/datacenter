<?php


/**
 * 基础控制类
 * 包含一些公用的控制方法
 *
 */
class BaseAction extends Action {

	/**
	 * 初始化控制器，用户登录SESSION控制
	 */
	public function _initialize() {
		//判断用户是否已经登录
		if (!isset ($_SESSION['LOGIN_USER'])) {
			$this->redirect("/Admin/");
		}
	}

	/**
	 * 获取session中存储的登录用户信息
	 */
	public function loginUser() {
		return session(C('SESSION_USER'));
	}

}
?>