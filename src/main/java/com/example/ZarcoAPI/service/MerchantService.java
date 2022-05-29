package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.dto.mapper.MerchantMapper;
import com.example.ZarcoAPI.dto.model.MerchantDto;
import com.example.ZarcoAPI.exception.ZarcoException;
import com.example.ZarcoAPI.exception.EntityType;
import com.example.ZarcoAPI.exception.ExceptionType;
import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.model.Merchant;
import com.example.ZarcoAPI.repository.IMerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MerchantService extends AbstractService<Merchant> implements IMerchantService{
    public MerchantService(IMerchantRepository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    /**
     * Fetches the specified merchant by id
     * @param id merchant identifier
     * @return MerchantDto
     */
    @Override
    public MerchantDto getMerchantById(String id) {
        Optional<Merchant> merchant = this.getRepository().findById(id);
        if (merchant.isPresent()){
            return MerchantMapper.toMerchantDto(merchant.get());
        }
        throw exception(EntityType.MERCHANT, ExceptionType.ENTITY_NOT_FOUND, id);
    }

    /**
     * Fetches all merchants from the database
     * @return List<MerchantDto>
     */
    @Override
    public List<MerchantDto> getAllMerchants() {
        return this.getRepository()
                .findAll()
                .stream()
                .map(merchant -> MerchantMapper.toMerchantDto(merchant))
                .collect(Collectors.toList());
    }

    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return ZarcoException.throwException(entityType, exceptionType, args);
    }
}
