$(function() {
	$("#list").hide();
	$("#search")
			.on(
					'click',
					function() {
						/* $("#result").html("查询结果"); */
						var ip = $.trim($("#search_text").val());
						var ip_url = 'http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js';
						if (ip != '') {
							check(ip);
							ip_url += '&ip=' + ip;
						}
						$
								.getScript(
										ip_url,
										function(_result) {
											if (remote_ip_info.ret == '1') {
												$("#list").show();
												$("#ip_start").html(
														remote_ip_info.start);
												$("#ip_end").html(
														remote_ip_info.end);
												$("#ip_city").html(
														remote_ip_info.city);
												$("#ip_province")
														.html(
																remote_ip_info.province);
												$("#ip_country").html(
														remote_ip_info.country);
												$("#ip_isp").html(
														remote_ip_info.isp);
												$("#ip_type").html(
														remote_ip_info.type);
												$("#ip_district")
														.html(
																remote_ip_info.district);
												$("#ip_desc").html(
														remote_ip_info.desc);

											} else {
												alert('网络错误,请稍后再试!');
											}
										});
					});
});
function check(ip) {
	str = ip;
	str = str.match(/(\d+)\.(\d+)\.(\d+)\.(\d+)/g);
	if (str == null) {
		alert("输入的IP地址无效");
		return false;
	} else if (RegExp.$1 > 255 || RegExp.$2 > 255 || RegExp.$3 > 255
			|| RegExp.$4 > 255) {
		alert("输入的IP地址无效");
		return false;
	} else {
		return true;
	}
}