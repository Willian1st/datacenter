var common = {
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
	upload : function(options) {
		var option = {
			formId : "#form",
			inputId : "#imageInput",
		}
		option = $.extend({}, option, options)
		var imagePath = $(option.inputId).val();
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
		var size = $(option.inputId)[0].files[0].size;
		if (size > allowSize) {
			layer.msg("图片大小请限制在1M以内");
			return false;
		}
		var index = null;
		$(option.formId).ajaxSubmit({
			type : 'POST',
			url : '/cms/upload/img',
			beforeSubmit : function() {
				index = layer.load(1);
			},
			success : function(data) {
				layer.close(index);
				if (data == -1) {
					layer.msg("请先配置上传路径！");
					return;
				}
			},
			error : function(data) {
				layer.close(index);
				layer.msg("上传失败，请检查网络后重试");
			}
		});

	}
}
