$(function() {
	window.location="google_maps.html";
    return;
    var remote="http://2.travelinfo.sinaapp.com/";
	var action = {
		editRow : undefined,// 只能一行一行的添加
		search : function() {
			$("#user").datagrid("load", {
				ip : $.trim($('input[name="ip"]').val()),
				from : $('input[name="from"]').val(),
				to : $('input[name="to"]').val(),
				address : $.trim($('input[name="address"]').val()),
				site : $.trim($('input[name="site"]').val()),
				type : $.trim($('input[name="type"]').val()),
				user_agent : $.trim($('input[name="user_agent"]').val()),
			});
		},

		// 添加一行
		add : function() {
			/*
			 * $("#user").datagrid("appendRow", { //末行添加 });
			 */
			$("#save,#redo").show();
			if (this.editRow == undefined) {
				$("#user").datagrid("insertRow", {
					index : 0,// 首行添加
					row : {}
				// 末行添加
				});
				// 将第一行设置成可编辑状态
				$("#user").datagrid("beginEdit", 0);
				this.editRow = 0;
			}
		},
		save : function() {
			// 将第一行设置成结束编辑状态
			$("#user").datagrid("endEdit", this.editRow);
		},
		edit : function() {
			var rows = $("#user").datagrid("getSelections");
			if (rows.length == 1) {
				if (this.editRow != undefined) {
					$("#user").datagrid("endEdit", this.editRow); // 提交未保存行
				}
				if (this.editRow == undefined) {
					var rowIndex = $("#user").datagrid("getRowIndex", rows[0]);
					$("#save,#redo").show();
					$("#user").datagrid("beginEdit", rowIndex);
					this.editRow = rowIndex;
					$("#user").datagrid("unselectRow", rowIndex);// 取消选中
				}
				// 产生修改建议
				advise(rows[0]);
			} else {
				$.messager.alert("警告", "只能选择一行修改!", "warning");
			}

		},
		del : function() {
			var rows = $("#user").datagrid("getSelections");
			if (rows.length > 0) {
				$.messager.confirm("确定操作", "您真的要删除所选择的记录吗!", function(flag) {
					if (flag) {
						var ids = [];
						for (var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : remote+"php/jdel.php",
							data : {
								ids : ids.join(',')
							},
							type : 'post',
							dataType : "json",
							success : function(data) {
								$.messager.alert("提示", "成功操作" + data.total
										+ "条记录!", "info");
								$("#user").datagrid("reload");
							}
						});
						console.log(ids.join(','));
					}
				});

			} else {
				$.messager.alert("提示", "请选择要删除的记录!", "info");
			}
		},
		redo : function() {
			$("#user").datagrid("rejectChanges");
			this.editRow = undefined;
			$("#save,#redo").hide();
		}
	};
	/*$('#data').layout({
		fit : true,
	});*/
	$("#user").datagrid({
		url : remote+"php/jlog4all.php",
		method : "post",
		width : "100%",
		title : "列表",
		striped : true,// 显示斑马线
		nowrap : true,// 不换行
		loadMsg : "拼命加载中...",
		rownumbers : "true",// 行号
		fitColumns : true,// 自适应宽度
		iconCls : "icon-search",
		toolbar : "#tb",
		rowStyler : function(index, row) {
			if (typeof (row.site) !== 'undefined') {
				if (row.site.indexOf('http://1.travelinfo.sinaapp.com') >= 0) {
					// class name also ok
					return 'color: #8FBC8F';
				}
			}
		},
		columns : [ [ {
			field : "id",
			title : "ID",
			halign : "center",
			checkbox : true
		}, {
			field : "ip",
			title : "地址(IP)",
			halign : "center",
			styler : function(value, row, index) {
				return 'font-weight:  bold';
			},
		}, {
			field : "type",
			title : "设备",
			sortable : true,
			halign : "center",
			formatter : function(value, row, index) {
				if (typeof (value) !== 'undefined') {
					if (value.indexOf('Other') >= 0) {
						return '[' + value + ']';
					} else {
						return value;
					}
				}
			},
			styler : function(value, row, index) {
				if (typeof (value) !== 'undefined') {
					if (value.indexOf('Other') >= 0) {
						return 'color:  #8B0000';
					}
				}

			},
			editor : {
				type : "validatebox",
				options : {
				// required : true
				}
			}
		}, {
			field : "address",
			title : "位置",
			halign : "center",
			width : '120px',
		}, {
			field : "time",
			title : "时间",
			sortable : true,
			halign : "center",
			width : '130px',
		}, {
			field : "site",
			title : "界面",
			sortable : true,
			halign : "center",
			align : "left",
		}, {
			field : "user_agent",
			title : "浏览器标识",
			sortable : true,
			halign : "left",
		},
		 {
			field : "request",
			title : "来源",
			sortable : true,
			halign : "left",
		},
		 {
			field : "infos",
			title : "详情",
			sortable : true,
			halign : "left",
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

		},
		onAfterEdit : function(rowIndex, rowData, changes) {
			console.log(rowData);
			$("#save,#redo").hide();
			action.editRow = undefined;
			// 执行保存
			$.ajax({
				url : remote+"php/jupdate.php",
				data : {
					id : rowData.id,
					type : rowData.type
				},
				type : 'post',
				dataType : "json",
				success : function(data) {
					if (data.total == 1) {
						$.messager.alert("提示", "修改成功!", "info");
						$("#user").datagrid("reload");
					} else {
						// $.messager.alert("提示", "没有修改...", "info");
					}

				}
			});
		},
		onDblClickRow : function(rowIndex, rowData) {
			if (action.editRow != undefined) {
				$("#user").datagrid("endEdit", action.editRow);
				// 提交未保存行
			}
			if (action.editRow == undefined) {
				$("#save,#redo").show();
				$("#user").datagrid("beginEdit", rowIndex);
				action.editRow = rowIndex;
				// 产生修改建议
				advise(rowData);
			}

			console.log(rowData);
		}
	});
});

// 扩展 dateTimeBox
$.extend($.fn.datagrid.defaults.editors, {
	datetimebox : {
		init : function(container, options) {
			var input = $('<input type="text">').appendTo(container);
			options.editable = false;
			input.datetimebox(options);
			return input;
		},
		getValue : function(target) {
			return $(target).datetimebox('getValue');
		},
		setValue : function(target, value) {
			$(target).datetimebox('setValue', value);
		},
		resize : function(target, width) {
			$(target).datetimebox('resize', width);
		},
		destroy : function(target) {
			$(target).datetimebox('destroy');
		},
	}
});
function advise(rowData) {
	var agent = rowData.user_agent;
	var deviceArray = [ "Windows Phone", "Tablet PC",// windows系列
	"MI 1S",// MI1系列
	"HM 1SC", "HM 1S", "HM NOTE",// HM系列
	"MI 4LTE", "MI 4C", "MI 4",// MI4系列
	"MI 3W", "MI 3",// MI3系列
	"MI 2S", "MI 2", // MI2系列
	"GT-I9300", "GT-I9268", "SM-G7108", "SM",// 三星系列
	"HONOR H30-L02", "HONOR", "HuaweiT8830", "HUAWEI T8950", "Huawei",// 华为系列
	"Z10",// 黑莓系列
	"HTC", // htc系列
	"LT26i",// 索尼系列
	"Nokia 920", "Nokia",// nokia系列
	"M032", // 魅族系列
	"iPhone", "iPod", "ios", "iPad", "Mac",// i系列
	"Android", "Unix", "BSD", "Linux" // linux系列
	, "Win" ];
	var browserArray = [ "Weibo", "TXMicroBlog", "MiuiBrowser", "UCBrowser",
			"MicroMessenger", "MQQBrowser", "IPadQQ", "Qzone", "QQ", "Chrome",
			"Opera", "Firefox", "MSIE", "Safari" ];

	var indexOfD = -1;
	var indexOfB = -1;
	for (i in deviceArray) {
		if (agent.match(eval('/' + deviceArray[i] + '/i'))) {
			indexOfD = i;
		}
		if (indexOfD >= 0) {
			break;
		}
	}
	for (i in browserArray) {
		if (agent.match(eval('/' + browserArray[i] + '/i'))) {
			indexOfB = i;
		}
		if (indexOfB >= 0) {
			break;
		}
	}
	if (indexOfD != -1 & indexOfB != -1) {
		var type = browserArray[indexOfB]
				+ ' -'
				+ (deviceArray[indexOfD] == 'Win' ? 'Windows'
						: deviceArray[indexOfD]);
		$.messager.confirm("确定操作", "建议修改为：<strong style='color:red'>" + type
				+ "</strong><br>点击'确定'可应用该建议<br>点击'取消'可手动修改", function(flag) {
			if (flag) {
				$("input.datagrid-editable-input").val(type);
			}
		});
	}
}