package com.ley.city.util;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * file upload util
 **/
public final class FileUtil {

	private static final Logger LOGGER=Logger.getLogger(FileUtil.class);
	
	/**
	 * {@link MultipartFile}
	 * **/
	private static MultipartFile multipartFile;
	
	/**
	 * upload file root and download root and this root directory must 
	 * exist in file system
	 * **/
	private static final String FILE_ROOT;
	
	
	/**
	 * file all name
	 * **/
	private static String fileName;
	
	/**
	 * file name
	 * **/
	private static String name;

	/**
	 * file relative path
	 * **/
	private static String fileRelativePath;
	
	static{
		FILE_ROOT="D:\\javaWorkspace\\ssmcity\\src\\main\\webapp\\upload";
		fileRelativePath="/upload/";
	}
	
	/**
	 * Upload file
	 * 
	 * @throws Exception
	 **/
	public static boolean upload(HttpServletRequest request) throws Exception {
		final String originalFilename = multipartFile.getOriginalFilename();
		if (multipartFile != null && originalFilename != null && originalFilename.length() > 0) {
			final String filePath = FILE_ROOT+"\\";
			//new file name and server save upload file copy
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(filePath+newFileName);
			//transfer file to root directory
			multipartFile.transferTo(newFile);
			fileName=filePath+newFileName;
			name=newFileName;
			LOGGER.info(fileName);
			return true;
		} else {
			return false;
		}
	}


	public static void setMultipartFile(MultipartFile multipartFile) {
		FileUtil.multipartFile = multipartFile;
	}
	
	
	/**
	 * file path and file name
	 * **/
	public static String getFileName() {
		return fileName;
	}
	
	/**
	 * file name
	 * **/
	public static String getName() {
		return name;
	}
	
	/**
	 * get relative path and this path has not open use
	 * **/
	public static String getFileRelativePath() {
		return fileRelativePath;
	}

}
