<?php


//用户模块
class HomeAction extends Action {
	public function index() {
		echo "Home";
	}
	public function insert() {
		$model = M('user');
		$data['username'] = "11";
		$data['code'] = "11";
		$data['createtime'] = time();
		echo $model->add($data);
	}
	public function find() {
		$id = I('post.id');
		$username = I('post.username');
		$page = I('post.page', 1);
		$rows = I('post.rows', 12);
		$model = M("user");
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
}