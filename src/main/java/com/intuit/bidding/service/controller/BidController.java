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

import com.intuit.bidding.service.entity.Bid;
import com.intuit.bidding.service.entity.Product;
import com.intuit.bidding.service.services.BidService;
import com.intuit.bidding.service.services.ProductCatalogueService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping("/bids/{id}")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public Bid getBidById(@PathVariable Long id){
        Optional<Bid> bid = bidService.getBidById(id);
        if (bid.isPresent()) {
            return bid.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find user");
        }
    }

    @GetMapping("/bids/")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public List<Bid> getAllBids(){
        log.info("Entered get All products API");
        return bidService.getAllBids();
    }

    @PostMapping("/bids/")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public ResponseEntity<String> addBids(@RequestBody Bid bid){
        bidService.saveBid(bid);
        return ResponseEntity.status(200).body("Success");
    }

    @DeleteMapping("/bids/{id}")
    @PreAuthorize("hasAnyAuthority('USER_ROLES','VENDOR_ROLES','ADMIN_ROLES')")
    public ResponseEntity<String> deleteBids(@PathVariable Long id){
        bidService.deleteBid(id);
        return ResponseEntity.status(200).body("Success");
    }


}

