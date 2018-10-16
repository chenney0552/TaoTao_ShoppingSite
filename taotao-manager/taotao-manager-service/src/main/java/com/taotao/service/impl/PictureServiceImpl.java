package com.taotao.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.utils.FastDFSClient;
import com.taotao.service.PictureService;

/**
 * 图片上传Service
 * @author Homeuser
 *
 */

@Service
public class PictureServiceImpl implements PictureService {

	@Value("${IMAGE_SERVER_BASE_URL}")
	private String IMAGE_SERVER_BASE_URL;
	
	@Override
	public PictureResult uploadPic(MultipartFile picFile) throws Exception {
		// TODO Auto-generated method stub
		
		PictureResult result = new PictureResult();
		
		//判断图片是否为空值
		if(picFile.isEmpty())
		{
			result.setError(1);
			result.setMessage("图片为空");
			return result;
		}
		try {
		//上传到图片服务器
		FastDFSClient client = new FastDFSClient("C:\\Users\\Homeuser\\eclipseEE-workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");	
		//取出图片的扩展名
		String originalFilename = picFile.getOriginalFilename();
		String extName    = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
		String url = client.uploadFile(picFile.getBytes(),extName);
		//拼接图片服务器的IP地址
		url = IMAGE_SERVER_BASE_URL + url;
		System.out.println(url);
		//把url响应给客户端
		result.setError(0);
		result.setUrl(url);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			result.setError(1);
			result.setMessage("图片上传失败");
		}
		return result;
	}
}
