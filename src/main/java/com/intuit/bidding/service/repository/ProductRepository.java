package com.intuit.bidding.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.intuit.bidding.service.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value="SELECT * from Product p where category=?1 AND bidding_start_time < now() AND bidding_end_time > now()", nativeQuery = true)
    List<Product> findByCategoryAndActiveNow(String category);
}
