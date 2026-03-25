package com.youpro.EComm_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.youpro.EComm_backend.model.Product;
import com.youpro.EComm_backend.service.ProductService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ProductController {
	@Autowired
	
	private ProductService service;

@GetMapping("products")
public ResponseEntity <List<Product>> getAllProducts(){
	return new ResponseEntity<List<Product>>(service.getAllProducts(),org.springframework.http.HttpStatus.OK);
}
@GetMapping("products/{id}")
public ResponseEntity<Product> getProductById(@PathVariable int id){
	
		Product product = service.getProductById(id);
		if(product != null) {
			return new ResponseEntity<Product>(product,org.springframework.http.HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Product>(org.springframework.http.HttpStatus.NOT_FOUND);
		}
		
}
@PostMapping("products")
public ResponseEntity<String> addProduct(@RequestPart Product product,@RequestPart MultipartFile image){
	try {
		Product product1 = service.addProduct(product, image);
		return new ResponseEntity<String>("Product added successfully",org.springframework.http.HttpStatus.OK);
	}
	catch(Exception e) {
		return new ResponseEntity<String>("Failed to add product",org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("products/{id}/image")
	public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
		Product product3 = service.getProductById(id);
		byte[] imageData = product3.getImageDate();
		return ResponseEntity.ok().contentType(org.springframework.http.MediaType.parseMediaType(product3.getImageType())).body(imageData);
	}

@PutMapping("products/{id}")
public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product,@RequestPart MultipartFile image){
	try {
		Product existingProduct = service.getProductById(id);
		if(existingProduct != null) {
			
			return new ResponseEntity<String>("Product updated successfully",org.springframework.http.HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Product not found",org.springframework.http.HttpStatus.NOT_FOUND);
		}
	}
	catch(Exception e) {
		return new ResponseEntity<String>("Failed to update product",org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
@DeleteMapping("products/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable int id){
	Product product4 = service.getProductById(id);
	if(product4 != null) {
		service.deleteProduct(id);
		return new ResponseEntity<String>("Product deleted successfully",org.springframework.http.HttpStatus.OK);
}
	else {
		return new ResponseEntity<String>("Product not found",org.springframework.http.HttpStatus.NOT_FOUND);
	}
}
@GetMapping("products/search")
public ResponseEntity<String> searchProducts(String keyword){
      List<Product> products = service.searchProducts(keyword);
      return new ResponseEntity<String>("Search results for keyword: " + keyword,org.springframework.http.HttpStatus.OK);
}
}
