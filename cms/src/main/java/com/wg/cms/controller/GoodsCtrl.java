package com.wg.cms.controller;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.wg.cms.bean.Goods;
import com.wg.cms.bean.GoodsCondition;
import com.wg.cms.bean.Property;
import com.wg.cms.service.IGoodsService;
import com.wg.cms.util.AjaxResultContext;
import com.wg.cms.util.DataBindingException;
import com.wg.cms.util.GoodsType;

@Controller
@RequestMapping("/goods")
public class GoodsCtrl extends BaseController {
	@Resource(name = "goodsService")
	private IGoodsService goodsService;
	@Autowired
	private Property property;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "")
	public ModelAndView index(Model model) {
		ModelAndView mv = CreateModelAndView("/goods/index");
		setCommon(mv);
		return mv;
	}

	/**
	 * 管理首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manage")
	public ModelAndView manage(Model model) {
		ModelAndView mv = new ModelAndView("/goods/manage");
		setCommon(mv);
		return mv;
	}

	/**
	 * 添加界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(Model model) {
		ModelAndView mv = new ModelAndView("/goods/add");
		setCommon(mv);
		return mv;
	}

	/**
	 * 修改界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.GET })
	public ModelAndView update(Model model, @PathVariable Integer id) {
		ModelAndView mv = new ModelAndView("/goods/update");
		Goods good = goodsService.selectByPrimaryKey(id);
		mv.addObject("good", good);
		setCommon(mv);
		return mv;
	}

	public void setCommon(ModelAndView mv) {
		mv.addObject("goodsType", GoodsType.getGoodsType());
		mv.addObject("manage", true);
	}

	/**
	 * 查看界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/view/{id}", method = { RequestMethod.GET })
	public ModelAndView view(Model model) {
		ModelAndView mv = new ModelAndView("/goods/view");
		return mv;
	}

	/**
	 * 综合查询
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(GoodsCondition condition) {
		List<Goods> pageList = goodsService.select(condition);
		condition.setData(pageList);
		return condition;
	}

	/**
	 * 综合查询总记录数
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/count")
	@ResponseBody
	public Object count(GoodsCondition condition) {
		long total = goodsService.count(condition);
		return total;
	}

	/**
	 * 新增
	 * 
	 * @param input
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.POST })
	@ResponseBody
	public AjaxResultContext add(@RequestBody @Valid Goods record, BindingResult result, HttpServletRequest request) {
		AjaxResultContext ajaxResult = new AjaxResultContext();
		if (result.hasErrors()) {
			ajaxResult.setResult(new DataBindingException(result));
		} else {
			ajaxResult.setResult(goodsService.insertSelective(record));
		}
		return ajaxResult;
	}

	/**
	 * 修改
	 * 
	 * @param record
	 * @param result
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/update/{id}", method = { RequestMethod.PUT })
	@ResponseBody
	public AjaxResultContext update(@RequestBody @Valid Goods record, BindingResult result, HttpServletRequest request) {
		AjaxResultContext ajaxResult = new AjaxResultContext();
		if (result.hasErrors()) {
			ajaxResult.setResult(new DataBindingException(result));
		} else {
			ajaxResult.setResult(goodsService.updateByPrimaryKeySelective(record));
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/del/{id}", method = { RequestMethod.DELETE })
	@ResponseBody
	public Object delete(@PathVariable Integer id, HttpServletRequest request) {
		return goodsService.deleteByPrimaryKey(id);
	}

	/**
	 * 图片上传
	 * 
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "/upload", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String uploadImage(HttpServletRequest request, @RequestParam(value = "file", required = true) MultipartFile file) {
		// String path =
		// request.getSession().getServletContext().getRealPath("/upload");
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

	/**
	 * 异常处理
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public @ResponseBody
	Object handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
		String result = "1";
		if (ex instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
			ex.printStackTrace();
			result = "-300";
		} else {
			result = "0";
		}
		return result;
	}
}
