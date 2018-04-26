var ue = UE.getEditor('editor', {
	toolbars : [
			[ 'fullscreen', 'pasteplain', 'undo', 'redo', 'bold', 'selectall',
					'cleardoc', 'drafts', 'source', 'link', 'emotion',
					'spechars' ],
			[ 'fontfamily', 'fontsize', 'removeformat', 'time', 'date',
					'unlink' ] ],
	initialFrameHeight : 200,
	autoHeightEnabled : true,
	autoFloatEnabled : true,
	enableAutoSave : true,
	saveInterval : 1000,
	maximumWords : 100,
});
ue.ready(function() {
	// 设置编辑器的内容
	ue.setContent('');
});
$(function() {
	// 初始化所有弹出框
	$('[data-toggle="popover"]').popover()
	$("#save,#blogSave").on("click", function() {
		var mtitle = $.trim($("#title").val());
		var mclass = $.trim($("#class").text());
		var mcontent = $.trim(ue.getContent());
		if (mtitle == "" || mclass == "" || mcontent == "") {
			$("#fullscreen").show();
			$("#nullAlert").show();
			setTimeout(function() {
				$("#nullAlert .close").trigger('click');
			}, 3000);
			return;
		}
		$.ajax({
			url : "../php/new/editblog.php",
			data : {
				"title" : mtitle,
				"class" : mclass,
				"content" : mcontent,
			},
			type : "post",
			success : function(data) {
				if (data == "1") {
					$("#fullscreen").show();
					$("#resultAlert").show();
					setTimeout(function() {
						$("#resultAlert .close").trigger('click');
					}, 3000);
				} else {
					$("#fullscreen").show();
					$("#errorAlert").show();
					setTimeout(function() {
						$("#errorAlert .close").trigger('click');
					}, 3000);
				}
			},
			error : function(data) {
				$("#fullscreen").show();
				$("#errorAlert").show();
				setTimeout(function() {
					$("#errorAlert .close").trigger('click');
				}, 3000);
			}
		})
	});
	$("#resultAlert .close").on("click", function() {
		$("#fullscreen").hide();
		$("#resultAlert").hide();
	});
	$("#errorAlert .close").on("click", function() {
		$("#fullscreen").hide();
		$("#errorAlert").hide();
	});
	$("#nullAlert .close").on("click", function() {
		$("#fullscreen").hide();
		$("#nullAlert").hide();
	});
})