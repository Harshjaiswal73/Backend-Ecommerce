package com.example.shopping.management.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.shopping.management.Dto.AdminProductResponseDto;
import com.example.shopping.management.Entity.AdminProduct;
import com.example.shopping.management.Service.ProductUploadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/upload")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AdminProductController {

	@Autowired
	private ProductUploadService productUploadService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<String> addproduct(@RequestParam("file") MultipartFile file,
											@ModelAttribute AdminProductResponseDto dto) {
		productUploadService.productlisting(file, dto);
		return ResponseEntity.ok("file uploaded successfully");
	}
	
	@GetMapping("/allProduct")
	public ResponseEntity<List<AdminProduct>>getproducts(){
		List<AdminProduct> products = productUploadService.getAllProduct();
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Optional<AdminProduct>>getproductbyid(@PathVariable Long id){
		return ResponseEntity.ok(productUploadService.getproductbyid(id));
	}
	@DeleteMapping("/product/{id}")
	public ResponseEntity<String>deletebyid(@PathVariable Long id){
		productUploadService.deletebyid(id);
		return ResponseEntity.ok("product delete sucessfully");
	}
	
}
