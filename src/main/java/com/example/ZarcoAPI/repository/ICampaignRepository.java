package com.example.ZarcoAPI.repository;

import com.example.ZarcoAPI.model.Campaign;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICampaignRepository extends MongoRepository<Campaign, String> {
}
