package com.ley.city.test.util;

import java.io.File;
import java.util.UUID;

import org.junit.Test;

public class TestFileUtil {

	@Test
	public void testFileUtils() throws Exception {
		final String filePath = System.getProperty("user.dir") + "\\upload \\";
		String newFileName = UUID.randomUUID() + "111.txt".substring("111.png".lastIndexOf("."));
		System.out.println(filePath + newFileName);
		File newFile = new File(filePath + newFileName);
		String filename = newFile.getName();
		System.out.println(filename);
		System.out.println((filePath + newFileName).replaceAll("\\\\", "/").replaceAll(" ", "%20"));
		System.out.println(
				"file:///D:/Program%20Files%20(x86)/tomcat/tomcat8/apache-tomcat-8.0.48-windows-x64/apache-tomcat-8.0.48/webapps/ssmcity/upload/47fbf039-d62b-482b-b6f9-f965013757fa.png"
						.equals("file:///D:/Program%20Files%20(x86)/tomcat/tomcat8/apache-tomcat-8.0.48-windows-x64/apache-tomcat-8.0.48/webapps/ssmcity/upload/47fbf039-d62b-482b-b6f9-f965013757fa.png"));
	}
}
