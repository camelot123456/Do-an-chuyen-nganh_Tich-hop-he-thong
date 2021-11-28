package com.pihotel.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileUtil {

	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		Path path = Paths.get(uploadDir);//Lấy đường dẫn của folder muốn hướng đến(tạo)

		if (!Files.exists(path)) { // Kiểm tra nếu folder chưa tồn tại
			Files.createDirectories(path); // Tạo một folder theo đường dẫn đã chọn
		}
		try (InputStream stream = multipartFile.getInputStream()) { //Lấy file ở thư mục đã chọn(bất kỳ)
			Path filePath = path.resolve(fileName); //Tạo đường dẫn đã đc giải quyết
			Files.copy(stream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			// TODO: handle exception
			throw new IOException("Could not save image file: " + fileName);
		}
	}
	
	public static void deleteFile(String uploadDir, String fileName) throws IOException {
		Path path = Paths.get(uploadDir);
		
		try {
			if (Files.exists(path)) {
				Path filePath = path.resolve(fileName);
				Files.delete(filePath);
			}
		} catch (IOException e) {
			// TODO: handle exception
			throw new IOException("Could not save image file: " + fileName);
		}
	}

}
