<?php


/**
 * 管理界面
 */

class IndexAction extends Action {
	public function index() {
		$this->display('signin');
		logHistory();
	}
	public function logout() {
		session('LOGIN_USER', null);
		$this->redirect('/Admin');
	}
	/**
	 * 登陆验证
	 */
	public function login() {
		$msg = "";
		$userName = I('post.username');
		$password = I('post.code');
		if ($userName && $password) {
			$model = M('user');
			$data = $model->where("username='%s' and code='%s'", $userName, $password)->find();
			if (!empty ($data)) {
				// 验证通过
				// session保存用户
				session('LOGIN_USER', $data);
				echo json_encode(array (
					"flag" => 0,
					
				));
			} else {
				$msg = "用户名或密码错误！";
				echo json_encode(array (
					"flag" => -1,
					"info" => $msg
				));
			}
		} else {
			$msg = "用户名或密码为空！";
			echo json_encode(array (
				"flag" => -2,
				"info" => $msg
			));
		}
	}
}
?>
