package com.taotao.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemService;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDescMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	@Override
	public TbItem getItemById(long itemId) {

		// 根据ID查询商品
		// TbItem item = itemMapper.selectByPrimaryKey(itemId);

		// example默认查询所有的商品
		TbItemExample example = new TbItemExample();

		// 创建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		List<TbItem> list = itemMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			TbItem item = list.get(0);
			return item;
		}
		return null;
	}

	@Override
	public EUDataGridResult getItemList(int page, int rows) {
		// TODO Auto-generated method stub

		// 查询商品列表
		TbItemExample example = new TbItemExample();

		// 分页处理
		PageHelper.startPage(page, rows);

		List<TbItem> list = itemMapper.selectByExample(example);

		// 创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();

		result.setRows(list);

		// 取出记录条数
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);

		result.setTotal(pageInfo.getTotal());

		return result;
	}

	/**
	 * 插入商品service
	 */
	@Override
	public TaotaoResult createItem(TbItem item, String desc, String itemParam) {
		// TODO Auto-generated method stub
		// 生成商品ID
		long itemId = IDUtils.genItemId();
		// 补全TbItem的属性
		item.setId(itemId);
		// 商品状态 1-正常 2-下架 3-删除
		item.setStatus((byte) 1);
		// 创建和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 插入商品表
		itemMapper.insert(item);
		// 商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 插入商品描述数据
		itemDescMapper.insert(itemDesc);

		// 添加商品规格参数处理
		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(itemParam);
		System.out.println("itemParam" + itemParam);
		itemParamItem.setCreated(date);
		itemParamItem.setUpdated(date);

		// 插入数据
		itemParamItemMapper.insert(itemParamItem);

		return TaotaoResult.ok();
	}

	@Override
	public String getItemParamHtml(Long itemId) {
		// TODO Auto-generated method stub
		// 根据商品ID查询规格参数

		TbItemParamItemExample example = new TbItemParamItemExample();
		com.taotao.pojo.TbItemParamItemExample.Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);

		// 执行查询
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.isEmpty()) {
			return "";
		}

		// 取出规格参数
		TbItemParamItem itemParamItem = list.get(0);
		// 取出json数据
		String paramData = itemParamItem.getParamData();
		// 转换成java对象
		List<Map> mapList = JsonUtils.jsonToList(paramData, Map.class);
		// 遍历List 生成HTML
		StringBuffer sb = new StringBuffer();

		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\">\n"); 
		sb.append("<tbody>\n");
		for(Map map:mapList) {
			sb.append("	<tr>\n");
			sb.append("		<th colspan=\"2\">"+map.get("group")+"</th>\n"); 
			sb.append("	</tr>\n");
			//取出规格项
			List<Map> mapList2 =  (List<Map>) map.get("params");
			for(Map map2 : mapList2) {
				sb.append("    <tr>\n"); 
				sb.append("    	<td>"+map2.get("k")+"</td>\n"); 
				sb.append("    	<td>"+map2.get("v") +"</td>\n"); 
				sb.append("    </tr>\n");
			}
		}
		
		sb.append("</tbody>\n");
		sb.append("</table>");

		return sb.toString();
	}
}