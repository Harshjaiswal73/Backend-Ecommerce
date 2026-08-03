package com.example.shopping.management.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.shopping.management.Dto.AdminProductResponseDto;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Repository.AdminProductRepository;
import com.example.shopping.management.Repository.UserRepository;

@Service
public class ProductUploadService {

	@Autowired
	private FileuploadService fileuploadService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminProductRepository productRepository;
	
	public void productlisting(MultipartFile file,AdminProductResponseDto dto) {
		
		// image upload
		String image = null;
		try {
			image = fileuploadService.uploadfile(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AdminProduct product = new AdminProduct();
		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setDescription(dto.getDescription());
		product.setBrand(dto.getBrand());
		product.setCategory(dto.getCategory());
		product.setImageUrl(image);
		product.setWrongprice(dto.getWrongprice());
		product.setCreatedAt(LocalDateTime.now());
		
		productRepository.save(product);
		System.out.println("Product Add Successfully");
	}
	// show products
	public List<AdminProduct> getAllProduct(){
		
		List<AdminProduct> products = productRepository.findAll();
		
		return products;
	}
	// show Product using id
	public Optional<AdminProduct>getproductbyid(Long id){
		return productRepository.findById(id);
	}
	
	// delete product using id
	public void deletebyid(Long id) {
			AdminProduct product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Product id not found"));
			productRepository.deleteById(id);		
	}

	
	
}
