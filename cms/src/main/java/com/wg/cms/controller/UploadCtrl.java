package com.wg.cms.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wg.cms.bean.Property;

@Controller
@RequestMapping("/upload")
public class UploadCtrl {
	@Autowired
	private Property property;

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/img", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadImage(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) {
		String path = property.localPath;
		if (property == null) {
			return "-1";
		}
		String fileName = file.getOriginalFilename();
		String prefix = fileName.substring(fileName.lastIndexOf("."));
		String newfileName = String.valueOf(System.currentTimeMillis()) + prefix;
		File targetFile = new File(path, newfileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		String fileUrl = "";
		try {
			file.transferTo(targetFile);
			fileUrl = request.getContextPath() + "/upload/" + newfileName;
		} catch (Exception e) {
			System.err.println("图片上传：" + e.getMessage());
			return "-1";
		}
		return fileUrl;
	}
}
