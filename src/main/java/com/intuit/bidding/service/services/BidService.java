package com.intuit.bidding.service.services;

import java.util.List;
import java.util.Optional;

import com.intuit.bidding.service.entity.Bid;

public interface BidService {
    List<Bid> getBidsForUser(Long userId);
    List<Bid> getBidsOnProduct(Long productId);
    List<Bid> getAllBids();
    Optional<Bid> getBidById(Long bidId);
    Bid saveBid(Bid bid);
    void deleteBid(Long bigId);
}
