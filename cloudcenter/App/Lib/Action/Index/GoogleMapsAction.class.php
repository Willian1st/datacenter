<?php


//谷歌地图模块
class GoogleMapsAction extends Action {
	public function index() {
		$this->display('google_maps');
		logHistory();
	}
}