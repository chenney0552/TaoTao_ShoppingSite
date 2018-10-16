package com.taotao.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * 商品分类查询服务
 * @author Homeuser
 *
 */

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	@Autowired
	private ItemCatService itemCatService;
	
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback)
	{
		
		ItemCatResult result = itemCatService.getItemCatList();
		
		if(StringUtils.isEmpty(callback)) {
			//result转换为字符串
			String json = JsonUtils.objectToJson(result);
			return json;
		}
		
		//字符串不为空 需要支持jsonp调用
		String json = JsonUtils.objectToJson(result);
		
		return callback + "(" + json + ");";
		 
	}
}
