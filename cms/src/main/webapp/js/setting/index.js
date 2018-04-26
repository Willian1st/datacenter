var app = angular.module("settingApp", []);
app.controller("setting", function($scope, $rootScope) {
	var ue = null;
	$scope.setting = {
		init : function() {
			$("#imageInput").on('change', function() {
				common.upload({
					formId : "#form",
					inputId : "#imageInput",
				})
			});
		},
		reset : function() {
			$("#imgSrc").attr("src", "");
			$('[name="picture"]').val("");
			$("#imageInput").val("");
		},
		manage : function() {
			var formData = common.convertArray($("#setting").serializeArray());
			$.ajax({
				url : "/cms/setting/" + formData.id,
				type : "put",
				contentType : 'application/json',
				data : JSON.stringify(formData),
				dataType : "json",
				success : function(response) {
					common.showAjaxResult(response);
				},
				error : function(data) {
				}
			});
			return false;
		}
	}
	$scope.setting.init();
});
