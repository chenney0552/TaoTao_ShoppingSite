package com.taotao.rest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.pojo.TbContentExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ContentService;

/**
 * 内容查询服务
 * 
 * @author Homeuser
 *
 */

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private JedisClient jedisClient;

	@Autowired
	private TbContentMapper contentMapper;

	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;

	@Override
	public List<TbContent> getContentList(Long cid) {

		// 添加缓存
		// 查询数据库之前先查询缓存,如果有直接返回
		try {
			// 从redis中取出缓存数据
			String json = jedisClient.hget(REDIS_CONTENT_KEY, cid + "");
			if (!StringUtils.isEmpty(json)) {
				// 把json转换为list
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		TbContentExample example = new TbContentExample();

		com.taotao.pojo.TbContentExample.Criteria criteria = example.createCriteria();

		criteria.andCategoryIdEqualTo(cid);

		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		// 返回结果之前,向缓存中添加数据
		try {
			// 为了规范key 可以使用hash
			// 定义一个保存内容的key,hash中每个项就是cid
			// value是list,需要转换为json
			jedisClient.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		//--------------------------------------------------
		System.out.println(list.toString());
		System.out.println("ContentService");
		//--------------------------------DEBUG-------------
		return list;
		
	}

	@Override
	public TaotaoResult syncContent(Long cid) {
		jedisClient.hdel(REDIS_CONTENT_KEY, cid+"");
		return TaotaoResult.ok();
	}

}
