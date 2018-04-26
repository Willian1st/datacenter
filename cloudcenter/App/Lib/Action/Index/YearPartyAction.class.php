<?php


//年会模块
class YearPartyAction extends Action {
	public function index() {
		//$this->display('index');
		if (preg_match('/(MicroMessenger)/i', $_SERVER['HTTP_USER_AGENT'])) {
			$this->display('shake');
		} else {
			$this->display('error');
		}
		logHistory();
	}
}