package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;
import com.taotao.portal.service.ContentService;

/**
 * 内容管理Service
 * 
 * @author Homeuser
 *
 */
@Service
public class ContentServiceImpl implements ContentService {

	/**
	 * 获得大广告位内容
	 */

	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;

	@Value("${REST_CONTENT_URL}")
	private String REST_CONTENT_URL;

	@Value("${REST_CONTENT_AD1_CID}")
	private String REST_CONTENT_AD1_CID;

	@Override
	public String getAd1List() {

		//不应该写死
		String json = HttpClientUtil.doGet("http://localhost:8081/rest/content/89");
		
		System.out.println(json);
		
		TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);

		List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();

		List<AdNode> resultList = new ArrayList<>();
		
		for(TbContent tbContent : contentList) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		
		//把resultList对象转化为json
		String resultJson =	JsonUtils.objectToJson(resultList);

		return resultJson;
	}
}