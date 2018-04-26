<?php
import("@.Action.Admin.BaseAction");
//用户模块
class LogAction extends BaseAction {
	public function index() {
		$this->display('log');
		logHistory();
	}

	public function find() {
		$id = I('post.id');
		$ip = I('post.ip');
		$host = I('post.host');
		$address = I('post.address');
		$startTime = I('post.startTime');
		$endTime = I('post.endTime');
		$include = I('post.include');
		$agent = I('post.agent');
		$ip = I('post.ip');
		$page = I('post.page', 1);
		$rows = I('post.rows', 10);
		$model = M("loghistory");
		$where = "1=1 ";
		if ($include == 'false' || empty ($include)) {
			$where = $where . "and host not like '%" . '/Admin/Log' . "%' ";
		}
		if (!empty ($ip)) {
			$where = $where . "and ip like '%" . $ip . "%' ";
		}
		if (!empty ($address)) {
			$where = $where . " and address like '%" . $address . "%' ";
		}
		if (!empty ($host)) {
			$where = $where . " and host like '%" . $host . "%' ";
		}
		if (!empty ($agent)) {
			$where = $where . " and agent like '%" . $agent . "%' ";
		}
		if (!empty ($startTime)) {
			$where = $where . " and visittime >=" . $startTime;
		}
		if (!empty ($endTime)) {
			$where = $where . " and visittime <=" . $endTime;
		}
		//echo $where;
		$total = $model->where($where)->count();
		$dataList = $model->where($where)->limit(($page -1) * $rows, $rows)->order('visittime desc')->select();
		$groupList = array ();
		if (!empty ($dataList)) {
			foreach ($dataList as $data) {
				array_push($groupList, $data);
			}
		}
		$groupList = ($groupList == null) ? "[]" : json_encode($groupList);
		$json = '{"total":' . $total . ',"rows":' . $groupList . '}';
		echo $json;
	}
}