package com.intuit.bidding.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.intuit.bidding.service.entity.Product;

@Service
public interface ProductCatalogueService {
    Product addProduct(Product product);
    Optional<Product> getProduct(Long productId);
    Product updateProduct(Product product);
    void deleteProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsActiveBiddingByCategory(String category);
}
