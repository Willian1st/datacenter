<?php
class LoginAction extends Action {
	public function index() {
		$this->display("signin");
	}
	public function login() {

	}
	public function verify() {
		import('ORG.Util.Image');
		Image :: buildImageVerify(4, 5, 'png');
	}
}
?>