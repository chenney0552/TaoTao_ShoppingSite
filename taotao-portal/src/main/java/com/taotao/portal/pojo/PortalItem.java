package com.taotao.portal.pojo;

import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.taotao.pojo.TbItem;

public class PortalItem extends TbItem {

	public String[] getImages()
	{
		String images = this.getImage();
		System.out.println(images);
		if(images!=null && !images.equals(""))
		{
			String[] imgs=images.split(",");
			System.out.println(imgs);
			return imgs;
		}
		return null;
	}
}
