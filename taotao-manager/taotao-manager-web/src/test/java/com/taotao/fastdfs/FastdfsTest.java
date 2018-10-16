package com.taotao.fastdfs;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

public class FastdfsTest {

	@Test
	public void testUpload() throws FileNotFoundException, IOException, Exception {
		
	//初始化全局配置
	ClientGlobal.init("C:\\Users\\Homeuser\\eclipseEE-workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
	
	TrackerClient trackerClient = new TrackerClient();
	
	TrackerServer trackerServer = trackerClient.getConnection();
	
	StorageServer storageServer = null;
	
	StorageClient storageClient = new StorageClient(trackerServer,storageServer);
	
	String[] strings = storageClient.upload_file("C:\\Users\\Homeuser\\Desktop\\Java.jpg", "jpg", null);
	
	for(String string : strings) {
		System.out.println(string);
	}
		
	}
	
	@Test
	public void testFastDfsClinet() throws Exception {
		
		FastDFSClient client = new FastDFSClient("C:\\Users\\Homeuser\\eclipseEE-workspace\\taotao-manager\\taotao-manager-web\\src\\main\\resources\\resource\\client.conf");
		
		String uploadFile = client.uploadFile("C:\\Users\\Homeuser\\Desktop\\battlefieldOne.jpg","jpg");
		
		System.out.println(uploadFile);
		
	}
		
}
