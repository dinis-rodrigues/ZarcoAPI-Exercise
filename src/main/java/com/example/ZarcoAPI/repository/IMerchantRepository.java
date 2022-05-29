package com.example.ZarcoAPI.repository;

import com.example.ZarcoAPI.model.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends MongoRepository<Merchant, String> {
}
