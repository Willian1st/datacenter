var app = angular.module("goodsApp", []);
app.controller("goodsAdd", function($scope, $rootScope) {
	$scope.goodsAdd = {
		init : function() {
			$("#image_input").on('change', $scope.goodsAdd.upload);
		},
		reset : function() {
			$("#imgSrc").attr("src", "");
			$('[name="picture"]').val("");
			$("#image_input").val("");
		},
		add : function() {
			var formData = $scope.goodsAdd.convertArray($("#goods").serializeArray());
			var url = "/cms/goods";
			var type = "post";
			if (formData.id) {
				url = url + "/update/" + formData.id;
				type = "put";
			} else {
				url = url + "/add";
			}
			$.ajax({
				url : url,
				type : type,
				contentType : 'application/json',
				data : JSON.stringify(formData),
				dataType : "json",
				success : function(response) {
					$scope.goodsAdd.showAjaxResult(response);
					if (response.success && response.result == 1) {
						if (formData.id) {
							layer.confirm("保存成功，回到列表？", function() {
								location.href = "/cms/goods/manage";
							});
						} else {
							layer.confirm("继续添加？", {
								btn : [ '确定', '回到列表' ], // 按钮
								shade : false, // 不显示遮罩
							}, function(index) {
								$("[type='reset']").click();
								layer.close(index);
							}, function() {
								location.href = "/cms/goods/manage";
							});
						}
					}
				},
				error : function(data) {
				}
			});
			return false;
		},
		/**
		 * 将jquery序列化后的值转为name:value的形式
		 */
		convertArray : function(o) {
			var v = {};
			for ( var i in o) {
				if (o[i].name != '__VIEWSTATE') {
					if (typeof (v[o[i].name]) == 'undefined')
						v[o[i].name] = o[i].value;
					else
						v[o[i].name] += "," + o[i].value;
				}
			}
			return v;
		},
		/**
		 * 显示后台操作结果
		 */
		showAjaxResult : function(response, rediretUrl) {
			if (response.success && response.result == 1) {
				layer.msg("操作成功", function() {
					if (rediretUrl) {
						location.href = rediretUrl;
					}
				});
			} else if (response.result && response.result.fieldErrors) {
				layer.msg("操作失败：<br\>" + this.displayAjaxErrors(response.result.fieldErrors));
			} else {
				layer.msg("操作失败");
			}
		},
		/**
		 * 后台错误提示
		 */
		displayAjaxErrors : function(errorList) {
			var errors = "";
			for ( var error in errorList) {
				errors += errorList[error] + "<br\>";
			}
			return errors;
		},
		convertImgToBase64 : function(url, callback, outputFormat) {
			var canvas = document.createElement('CANVAS'), ctx = canvas.getContext('2d'), img = new Image;
			img.crossOrigin = 'Anonymous';
			img.onload = function() {
				canvas.height = img.height;
				canvas.width = img.width;
				ctx.drawImage(img, 0, 0);
				var dataURL = canvas.toDataURL(outputFormat || 'image/png');
				callback.call(this, dataURL);
				canvas = null;
			};
			img.src = url;
		},
		upload : function() {
			var imagePath = $("#image_input").val();
			if (imagePath == "") {
				layer.msg("请选择图片");
				return false;
			}
			var strExtension = imagePath.substr(imagePath.lastIndexOf('.') + 1);
			if (strExtension != 'jpg' && strExtension != 'gif' && strExtension != 'png' && strExtension != 'bmp') {
				layer.msg("请选择图片类型的文件");
				return false;
			}
			var allowSize = 1 * 1024 * 1024;// 1M
			if ($("#image_input")[0].files) {
				var size = $("#image_input")[0].files[0].size;
				if (size > allowSize) {
					layer.msg("图片大小请限制在1M以内");
					return false;
				}
			}
			var index = null;
			$("#form").ajaxSubmit({
				type : 'POST',
				url : '/cms/goods/upload',
				beforeSubmit : function() {
					index = layer.load(1);
				},
				success : function(data) {
					layer.close(index);
					if (data == -1) {
						layer.msg("请先配置上传路径！");
						return;
					}
					$("#imgSrc").attr("src", data);
					$('[name="picture"]').val(data);
				},
				error : function(data1, data2, data) {
					layer.close(index);
					layer.msg("上传失败，请检查网络后重试");
				}
			});

		}
	};
	$scope.goodsAdd.init();
	window.onload = function() {
		return;
		var input = $("#picture");
		var result = $("#base64");
		var imgSrc = $("#imgSrc");
		if (typeof (FileReader) === 'undefined') {
			result.innerHTML = "抱歉，请使用现代浏览器操作！";
			input.setAttribute('disabled', 'disabled');
		} else {
			input.on('change', readFile);
		}
		function readFile() {
			var file = this.files[0];
			if (!/image\/\w+/.test(file.type)) {
				layer.msg("请选择图片");
				input.val("");
				return false;
			}
			var reader = new FileReader();
			reader.readAsDataURL(file);
			reader.onload = function(e) {
				result.val(this.result);
				imgSrc.attr("src", this.result);
			};
		}
	};
});
