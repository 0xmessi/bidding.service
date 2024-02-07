package com.intuit.bidding.service.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double basePrice;
    private String imageUrl;
    private String Category;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bidding_start_time")
    private Date biddingStartTime;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "bidding_end_time")
    private Date biddingEndTime;

}
