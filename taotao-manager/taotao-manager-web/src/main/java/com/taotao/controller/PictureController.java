package com.taotao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;

/**
 * 图片上传Controller
 * @author Homeuser
 *
 */

@Controller
public class PictureController {

	@Autowired
	private PictureService pictureService;
	
	//解决IE的兼容问题
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String uploadFile(MultipartFile uploadFile) throws Exception {
		PictureResult result = pictureService.uploadPic(uploadFile);
		//需要把Java对象手工转换为json数据
		String json = JsonUtils.objectToJson(result);
		return json;
	}
	
//	public PictureResult uploadFile(MultipartFile uploadFile) throws Exception {
//		PictureResult result = pictureService.uploadPic(uploadFile);
//		return result;
//	}
		
}
