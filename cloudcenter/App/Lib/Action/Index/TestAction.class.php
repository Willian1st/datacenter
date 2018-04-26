<?php
class TestAction extends Action {
	public function index() {
		logHistory();
		$this->display('iphonetest');
	}
	public function webchatTest() {
		logHistory();
		$this->display('webchatTest');
	}
}
?>
