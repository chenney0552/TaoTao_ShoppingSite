package com.taotao.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerTest {

	@Test
	public void testFreeMarker() throws Exception{
		
		Configuration configuration = new Configuration(Configuration.getVersion());
		configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Homeuser\\eclipseEE-workspace\\taotao-portal\\src\\main\\webapp\\WEB-INF\\ftl"));
		configuration.setDefaultEncoding("utf-8");
		Template template = configuration.getTemplate("first.ftl");
		Map root = new HashMap<>();
		root.put("hello", "hello freemarker");
		Writer out = new FileWriter(new File("D:\\html\\hello.html"));
		template.process(root, out);
		out.flush();
		out.close();
	}
	
	
	
	
}
