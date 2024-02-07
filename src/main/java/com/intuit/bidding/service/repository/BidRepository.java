package com.intuit.bidding.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.intuit.bidding.service.entity.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid,Long>{
    List<Bid> findByUserId(Long userId);
    List<Bid> findByProductId(Long productId);
}
