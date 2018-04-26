$(function() {
	$("#convert").on("click", function() {
		var num = $.trim($("#num").val());
		var re = /^[0-9]+.?[0-9]*$/;
		if (!re.test(num)) {
			alert("请输入数字金额...");
			return;
		}
		$.ajax({
			url : "../php/rmb.php",
			type : "get",
			// dataType : "jsonp",
			data : {
				num : num
			},
			success : function(data) {
				$("#result").html(data);
			}
		});
	});
});
