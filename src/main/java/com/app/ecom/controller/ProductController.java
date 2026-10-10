package com.app.ecom.controller;

import com.app.ecom.dto.ProductRequest;
import com.app.ecom.dto.ProductResponse;
import com.app.ecom.model.Product;
import com.app.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productservice;
//
//    public ProductController(ProductService productservice) {
//        this.productservice = productservice;
//    }

    @PostMapping
    public ResponseEntity<Optional<ProductResponse>> createProduct (@RequestBody ProductRequest productRequest) {

        Optional<ProductResponse> productResponse = productservice.createProduct(productRequest);
        if (productResponse.isPresent()) {
            return ResponseEntity.ok(productResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> updateProduct (@PathVariable Long id, @RequestBody ProductRequest productRequest) {

        Optional<ProductResponse> productResponse = productservice.updateProduct(id, productRequest);
        if (productResponse.isPresent()) {
            return ResponseEntity.ok(productResponse);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Optional<ProductResponse>>> getProduct () {

        List<Optional<ProductResponse>> productResponse = productservice.getProduct();
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id) {
        boolean deleted = productservice.deleteProduct(id);
        return (deleted) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping("/search")
    public ResponseEntity<List<Optional<ProductResponse>>> searchProduct (@RequestParam String keyword) {
        List<Optional<ProductResponse>> productResponse = productservice.searchProduct(keyword);
        return ResponseEntity.ok(productResponse);

    }
}

