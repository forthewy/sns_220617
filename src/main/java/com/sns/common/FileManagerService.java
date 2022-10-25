package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\parkjeesoo\\sns\\workspace\\images/";
			
			// "D:\\Jane\\spring-project\\sns\\workspace\\images/"; 노트북 주소
	
	public String saveFile(String userLogInId, MultipartFile file) {
		String directoryName = userLogInId + "_" + System.currentTimeMillis() + "/";
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		if (directory.mkdir() == false) {
			return null;
		}
		
		
		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
		
 	}
}