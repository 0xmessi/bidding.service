package com.intuit.bidding.service.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.intuit.bidding.service.entity.Bid;
import com.intuit.bidding.service.repository.BidRepository;

@Service
public class BidServiceImpl implements BidService{

    @Autowired
    BidRepository bidRepository;

    @Override
    public List<Bid> getBidsForUser(Long userId) {
        return bidRepository.findByUserId(userId);
    }

    @Override
    public List<Bid> getBidsOnProduct(Long productId) {
        return bidRepository.findByProductId(productId);
    }

    @Override
    public Optional<Bid> getBidById(Long bidId) {
        return bidRepository.findById(bidId);
    }

    @Override
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public void deleteBid(Long bidId) {
        bidRepository.deleteById(bidId);
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }
    
}
