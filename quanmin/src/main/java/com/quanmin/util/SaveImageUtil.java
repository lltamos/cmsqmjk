package com.quanmin.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 保持图片
 * 
 * @author yang
 *
 */
public class SaveImageUtil {

	public static String saveImage(HttpServletResponse response, MultipartFile fileName,String basePath,String visitUrl) throws IOException {

		// 获取文件类型
		String fileType = fileName.getContentType();
		// 获取文件名称
		String originalFilename = fileName.getOriginalFilename();
		String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
		String randomUUID = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
//		System.out.println("oldFileName::" + originalFilename);
		String newName = randomUUID + suffix;
		String filePath = basePath + newName;
		File file = new File(filePath);
		// url地址
		visitUrl = visitUrl + newName;
		if (!file.getParentFile().exists()) {
			boolean mkBack = file.getParentFile().mkdirs();
		}
		FileUtils.copyInputStreamToFile(fileName.getInputStream(), file);
		return visitUrl;
	}
}
