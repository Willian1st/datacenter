<?php


//用户模块
class DeviceAction extends BaseAction {
	public function index() {
		$this->display('device');
		logHistory();
	}
	/**
	 * 获取设备列表
	 */
	public function find() {
		$id = I('post.id');
		$username = I('post.username');
		$page = I('post.page', 1);
		$rows = I('post.rows', 12);
		$model = M("device");
		$total = $model->where("", $username)->count();
		$where = "";
		if (!empty ($where)) {
			$where = $where . " userid =" . $id;
		}
		if (!empty ($username)) {
			$where = $where . " and userid =" . $username;
		}
		echo $where;
		$dataList = $model->where($where)->limit(($page -1) * $rows, $rows)->select();
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
	/**
	 * 修改权限
	 */
	public function update() {
		$model = M('device');
		$flag = $_POST['flag'];
		$username = $_POST['username'];
		$id = $_POST['id'];
		$result = M('device');
		echo $result->where('id=' . $id)->setField($data = array (
			'flag' => $flag,
			'username' => $username
		));
	}
}