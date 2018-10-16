package com.taotao.httpclient;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.csource.common.NameValuePair;
import org.junit.Test;

public class HttpClientTest {

	@Test
	public void testHttpGet() throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet get = new HttpGet("http://www.itcast.com");

		CloseableHttpResponse response = httpClient.execute(get);

		HttpEntity entity = response.getEntity();

		String result = EntityUtils.toString(entity);

		//System.out.println(result);

		response.close();

		httpClient.close();
	}

	@Test
	public void testHttpPost() throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost post = new HttpPost("http://localhost:8082/posttest.html");

		List<NameValuePair> formList = new ArrayList<>();

		formList.add(new NameValuePair("name", "zhangsan"));
		formList.add(new NameValuePair("pass", "123456"));

		@SuppressWarnings("unchecked")
		StringEntity entity = new UrlEncodedFormEntity((List<? extends org.apache.http.NameValuePair>) formList,"utf-8");
		
		
		post.setEntity(entity);
		
		CloseableHttpResponse response = httpClient.execute(post);
		
		HttpEntity httpEntity = response.getEntity();
		
		String result = EntityUtils.toString(httpEntity);
		
		System.out.println(result);
		
		response.close();
		
		httpClient.close();
	}

}
