package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.exception.EntityType;
import com.example.ZarcoAPI.exception.ExceptionType;
import com.example.ZarcoAPI.exception.ZarcoException;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public abstract class AbstractService<T> {
    protected final MongoRepository<T, String> repository;
    protected final MongoTemplate mongoTemplate;

    protected boolean isLimitAllowed(int limit) {
        long count = repository.count();
        if(limit > count) {
            return false;
        }
        return true;
    }

    protected boolean isLimitWithOffsetAllowed(int limit, int offset) {
        long count = repository.count();
        if(limit + offset > count) {
            return false;
        }
        return true;
    }

    RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ZarcoException.throwException(entityType, exceptionType, args);
    }
}
