package com.example.shopping.management.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class FileuploadService {

	@Autowired
	private Cloudinary cloudinary;
	
//	public String uploadfile(MultipartFile file) throws IOException {
//	
//		try {
//			Map uploadimage = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.emptyMap());
//			return (String)uploadimage.get("url");
//		} catch (Exception e) {
//			// TODO: handle exception
//			 throw new RuntimeException("file uploading failed"); 
//		}
//
//	}
	public String uploadfile(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            Map uploadimage = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.emptyMap()
            );

            return uploadimage.get("secure_url").toString();

        } catch (Exception e) {
            e.printStackTrace(); // 🔥 MOST IMPORTANT
            throw new RuntimeException("Cloudinary upload failed: " + e.getMessage());
        }
    }
	
}
