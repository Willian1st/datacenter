<?php


//接口
class IAction extends Action {
	/**
	 * 设备新增
	 */
	public function deviceAdd() {
		$model = M('device');
		$data['time'] = time();
		$deviceid = '-1';
		$userid = '-1';
		$sim = '-1';
		$where = ' 1=1 ';
		//设备唯一ID
		if (isset ($_POST['deviceid']) && !empty ($_POST['deviceid'])) {
			$deviceid = $_POST['deviceid'];
			$data['deviceid'] = $_POST['deviceid'];
			$where = $where . " and deviceid='$deviceid' ";
		}
		if (isset ($_POST['userid']) && !empty ($_POST['userid'])) {
			$userid = $_POST['userid'];
			$data['userid'] = $_POST['userid'];
			$where = $where . " and userid='$userid' ";
		}
		// 插了sim卡
		if (isset ($_POST['sim']) && !empty ($_POST['sim'])) {
			$sim = $_POST['sim'];
			$data['sim'] = $sim;
			$where = $where . " and sim='$sim' ";
		}
		$total = $model->where($where)->count();
		if ($total <= 0) {
			$model->add($data);
			echo '{"result":"device add success! ' . 'total ' . $total . '"}';
		} else {
			echo '{"result":"device add failed! ' . 'total ' . $total . '' . ' sim:' . $_POST['sim'] . ' userid:' . $_POST['userid'] . ' deviceid:' . $_POST['deviceid'] . '"}';
		}
	}
	/**
	 * 检察权限
	 */
	public function deviceCheck() {
		$model = M('device');
		$sim = $_POST['sim'];
		$deviceid = $_POST['deviceid'];
		$userid = $_POST['userid'];
		$where = " sim='$sim' and deviceid='$deviceid' and userid='$userid'";
		$dataList = $model->where($where)->select();
		$groupList = array ();
		//更新检查时间
		if (!empty ($dataList)) {
			foreach ($dataList as $data) {
				array_push($groupList, $data); //已记录的设备列表
				$id = $data['id'];
				$result = M('device');
				$result->where('id=' . $id)->setField($data = array (
					'checktime' => time()
				));
			}
		}
		$total = $model->where($where)->count();
		$groupList = ($groupList == null) ? "[]" : json_encode($groupList);
		$json = '{"total":' . $total . ',"rows":' . $groupList . '}';
		echo $json;
	}

	/**
	 * 检查新版本
	 */
	public function deviceUpdate() {
		echo '{"version":20160711}';
	}
	/**
	 * 应用信息新增
	 */
	public function redbagAdd() {
		$agent = $_SERVER['HTTP_USER_AGENT'];
		$ip = get_client_ip();
		$host = $_SERVER['SERVER_NAME'] . ':' . $_SERVER["SERVER_PORT"] . $_SERVER["REQUEST_URI"];
		$model = M('loghistory');
		$data['visittime'] = time();
		$data['agent'] = $agent;
		$data['ip'] = $ip;
		$data['host'] = $host;
		$data['request'] = $_POST['request'];
		$data['infos'] = $_POST['infos'];
		$data['detail'] = $_POST['detail'];
		$total = 0;
		if (isset ($_POST['infos']) && !empty ($_POST['infos'])) {
			//判断是否已存在
			$infos = trim($data['infos']);
			$where = " trim(infos)='$infos' ";
			$result = M('loghistory');
			$total = $result->where($where)->count();
		}
		if ($total <= 0) {
			$address = '';
			$addr = getaddress($ip);
			foreach ($addr[0] as $value) {
				$address = $address . $value . " ";
			}
			$data['address'] = ($address == "" ? "未知" : $address);
			$model->add($data);
			echo '{"result":"redbag add success! ' . 'total ' . $total . ' ip:' . $ip . '"}';
		} else {
			echo '{"result":"redbag add failed! ' . 'total ' . $total . '' . ' infos:' . $_POST['infos'] . ' ip:' . $ip . ' deviceid:' . $_POST['deviceid'] . '"}';
		}
	}
}