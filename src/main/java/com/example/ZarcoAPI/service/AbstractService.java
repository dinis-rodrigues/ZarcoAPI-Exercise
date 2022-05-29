package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.model.Campaign;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Service
@AllArgsConstructor
public abstract class AbstractService<T> {
    private final MongoRepository<T, String> repository;
    private final MongoTemplate mongoTemplate;

    boolean isLimitAllowed(int limit) {
        long count = repository.count();
        if(limit > count) {
            return false;
        }
        return true;
    }

    boolean isLimitWithOffsetAllowed(int limit, int offset) {
        long count = repository.count();
        if(limit + offset > count) {
            return false;
        }
        return true;
    }
}
