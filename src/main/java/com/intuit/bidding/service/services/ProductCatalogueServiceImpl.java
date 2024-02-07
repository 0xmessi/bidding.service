package com.intuit.bidding.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.bidding.service.entity.Product;
import com.intuit.bidding.service.repository.ProductRepository;

@Service
public class ProductCatalogueServiceImpl implements ProductCatalogueService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);

    }

    @Override
    public Optional<Product> getProduct(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(updatedProduct.getId());
        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setBiddingStartTime(updatedProduct.getBiddingStartTime());
            existingProduct.setBiddingEndTime(updatedProduct.getBiddingEndTime());
            return productRepository.save(existingProduct);
        } else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsActiveBiddingByCategory(String category) {
        return productRepository.findByCategoryAndActiveNow(category);
    }
    
}
