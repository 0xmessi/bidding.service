package com.intuit.bidding.service.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.intuit.bidding.service.entity.Product;
import com.intuit.bidding.service.services.ProductCatalogueService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class ProductController {

    @Autowired
    private ProductCatalogueService productCatalogueService;

    @GetMapping("/product/{id}")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public Product getProductById(@PathVariable Long id){
        Optional<Product> product = productCatalogueService.getProduct(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user");
        }
    }

    @GetMapping("/product/")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public List<Product> getAllProducts(){
        log.info("Entered get All products API");
        return productCatalogueService.getAllProducts();
    }


    @GetMapping("/product/active_bidding")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public List<Product> getProductsWithActiveBidding(@RequestParam String category){
        return productCatalogueService.getProductsActiveBiddingByCategory(category);
    }

    @PostMapping("/product/")
    @PreAuthorize("hasAnyAuthority('VENDOR_ROLES','ADMIN_ROLES')")
    public ResponseEntity<String> addProduct(@RequestBody Product product){
        log.info("Entered Add product API");
        productCatalogueService.addProduct(product);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/product/{id}")
    @PreAuthorize("hasAnyAuthority('VENDOR_ROLES','ADMIN_ROLES')")
    public ResponseEntity<String> addProduct(@PathVariable Long id){
        productCatalogueService.deleteProduct(id);
        return ResponseEntity.status(200).body("Success");
    }
}
