$(function() {
	/*$('#data').layout({
		fit : true,
	});*/
	$("#user").datagrid({
		url : "../php/jlog.php",
		method : "post",
		width : "100%",
		title : "列表",
		striped : "true",
		loadMsg : "拼命加载中...",
		rownumbers : "true",
		iconCls : "icon-search",
		fitColumns : true,
		rowStyler : function(index, row) {
			if (row.site == 'travelinfo.sinaapp.com') {
				// class name also ok
				return 'color: #006400';
			}
		},
		columns : [ [ {
			field : "ip",
			title : "IP",
		// width : "10%",
		}, {
			field : "address",
			title : "地址",
		// width : "15%",
		}, {
			field : "time",
			title : "时间",
			sortable : true,
		// width : "20%",
		}, {
			field : "site",
			title : "界面",
			sortable : true,
			// width : "15%",
			align : "right",
		}, {
			field : "user_agent",
			title : "浏览器标识",
			sortable : true,
		// width : "90%",
		} ] ],
		pagination : true,
		pageSize : 20,
		pageList : [ 20, 40, 60, 80, 100 ],
		pageNumber : 1,
		pagePosition : "both",
		sortName : "time",
		sortOrder : "desc",
		// 默认排序方式
		remoteSort : false,
		queryParams : {

		}

	});

});