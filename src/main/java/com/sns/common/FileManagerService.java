package com.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManagerService {
	

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public static final String FILE_UPLOAD_PATH = "D:\\Jane\\spring-project\\sns\\workspace\\images/";
			//  노트북 주소
			//"D:\\parkjeesoo\\sns\\workspace\\images/";
	
	
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
	
	public void deleteFile(String imgPath) {
		Path path = Paths.get(FILE_UPLOAD_PATH + imgPath.replace("/images/", ""));
		
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.error("[이미지 삭제] 이미지 삭제 실패 imagePath:{}" , imgPath);
			}
		}
		
		path = path.getParent();
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				log.error("[디렉토리 삭제] 디렉토리 삭제 실패 imagePath:{}" , imgPath);
				
			}
		}
	}
}
