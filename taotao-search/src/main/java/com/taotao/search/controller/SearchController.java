package com.taotao.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.search.pojo.SearchResult;
import com.taotao.search.service.SearchService;
import static java.nio.charset.StandardCharsets.*;

import java.nio.charset.Charset;


/**
 * 发布搜索服务
 * @author Homeuser
 * 测试请求: localhost:8083/search/q?keyword=手机&page=1&rows=10
 */

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/q")
	@ResponseBody
	public TaotaoResult search(@RequestParam(defaultValue="")String keyword, 
			@RequestParam(defaultValue="1")  Integer page, 
			@RequestParam(defaultValue="30") Integer rows)  {
		
		try {
			//转换字符集
			byte[] ptext = keyword.getBytes("ISO-8859-1"); 
			keyword = new String(ptext,Charset.forName("UTF-8")); 
			SearchResult searchResult = searchService.search(keyword, page, rows);
			return TaotaoResult.ok(searchResult);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}	
	}
}