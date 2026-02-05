package com.example.ecommerce.Controller;

import com.example.ecommerce.DTO.CreateDTO.ProductCreateDTO;
import com.example.ecommerce.DTO.CreateDTO.ReviewCreateDTO;
import com.example.ecommerce.DTO.ResponseDTO.ProductResponseDTO;
import com.example.ecommerce.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> saveProduct(@RequestBody ProductCreateDTO productCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(productCreateDTO));
    }



    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
