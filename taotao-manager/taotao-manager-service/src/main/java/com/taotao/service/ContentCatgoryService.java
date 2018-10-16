package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {

	List<EasyUITreeNode> getContentCatList(Long parentId);

	TaotaoResult insertCatgory(Long parentId, String name);
}
