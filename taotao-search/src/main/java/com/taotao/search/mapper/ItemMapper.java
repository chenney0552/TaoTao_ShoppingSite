package com.taotao.search.mapper;

import java.util.List;

import com.taotao.search.pojo.SearchItem;

public interface ItemMapper {

	//查询所有
	List<SearchItem> getItemList();
	
}
