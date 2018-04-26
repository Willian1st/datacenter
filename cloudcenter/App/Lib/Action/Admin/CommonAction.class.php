<?php


/**
 * 公共页面
 */

class CommonAction extends Action {
	public function nav() {
		$this->display("nav");
	}
	public function header() {
		$this->display("header");
	}
	public function footer() {
		$this->display("footer");
	}
}
?>
