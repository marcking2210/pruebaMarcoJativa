package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Product;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ProductRepository;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RequestMapping("/api/product")
public class ProductService {
	
	@Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    private Product create( @RequestBody Product product) {
    	productRepository.save(product);
        return product;
    }

    @GetMapping("/{product}")
    private Product read(@PathVariable(value = "product") Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }

    @GetMapping("/all")
    private List<Product> all() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return products;
    }

    @PutMapping("/update")
    private ResponseEntity<?> udpate(@PathVariable(value = "product") Integer productId, @RequestBody Product productDetail) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        product.setProductCode(productDetail.getProductCode());
        product.setProductName(productDetail.getProductName());
        product.setProductPrice(productDetail.getProductPrice());
        productRepository.save(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{product}")
    private ResponseEntity<?> delete(@PathVariable(value = "product") Integer productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

}
