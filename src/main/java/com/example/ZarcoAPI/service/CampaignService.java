package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.dto.mapper.CampaignMapper;
import com.example.ZarcoAPI.dto.model.CampaignDto;
import com.example.ZarcoAPI.exception.EntityType;
import com.example.ZarcoAPI.exception.ExceptionType;
import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.repository.ICampaignRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CampaignService extends AbstractService<Campaign> implements ICampaignService{

    public CampaignService(ICampaignRepository repository, MongoTemplate mongoTemplate) {
        super(repository, mongoTemplate);
    }

    /**
     * Fetches all campaigns from the database
     *
     * @return List<CampaignDto>
     */
    @Override
    public List<CampaignDto> getAllCampaigns() {
        return repository
                .findAll()
                .stream()
                .map(CampaignMapper::toCampaignDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetches all campaigns, that still has budget, up to a maximum of limit campaigns.
     *
     * @param limit maximum campaigns
     * @return List<CampaignDto>
     */
    public List<CampaignDto> getCampaigns(int limit) {
        if(!isLimitAllowed(limit)){
            throw exception(EntityType.CAMPAIGN,
                    ExceptionType.ENTITY_EXCEPTION,
                    "Limit is off-limits of collection");
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("budget")
                .gt(0))
                .with(Sort.by(Sort.Direction.DESC, "budget"))
                .limit(limit);

        return mongoTemplate
                .find(query, Campaign.class, "campaigns")
                .stream()
                .map(CampaignMapper::toCampaignDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetches all campaigns, that still has budget, up to a maximum of limit campaigns, and starting at offset
     *
     * @param limit maximum campaigns
     * @param offset start at
     * @return List<CampaignDto>
     */
    public List<CampaignDto> getCampaigns(int limit, int offset) {
        if(!isLimitWithOffsetAllowed(limit, offset)){
            throw exception(EntityType.CAMPAIGN,
                    ExceptionType.ENTITY_EXCEPTION,
                    "Limit and offset are off-limits of collection");
        }

        // Skip to offset and take limit amount of campaigns
        Query query = new Query();
        query.addCriteria(Criteria.where("budget")
                .gt(0))
                .with(Sort.by(Sort.Direction.DESC, "budget"))
                .skip(offset)
                .limit(limit);

        return mongoTemplate
                .find(query, Campaign.class, "campaigns")
                .stream()
                .map(CampaignMapper::toCampaignDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetches the specified campaign by id
     *
     * @param id campaign identifier
     * @return CampaignDto
     */
    @Override
    public CampaignDto getCampaignById(String id) {
        Optional<Campaign> campaign = repository.findById(id);
        if (campaign.isPresent()){
            return CampaignMapper.toCampaignDto(campaign.get());
        }
        throw exception(EntityType.CAMPAIGN, ExceptionType.ENTITY_NOT_FOUND, id);
    }

    /**
     * Subtracts an amount to the remaining budget of the specified campaign
     *
     * @param id campaign identifier
     * @param amount amount to be consumed
     * @return CampaignDto consumed campaign
     */
    @Override
    public CampaignDto consumeBudget(String id, double amount) {
        Optional<Campaign> campaign = repository.findById(id);
        if(!campaign.isPresent()){
            throw exception(EntityType.CAMPAIGN, ExceptionType.ENTITY_NOT_FOUND, id);
        }

        Campaign campaignObj = campaign.get();
        double newBudget = campaignObj.getBudget() - amount;

        if(newBudget < 0) {
            throw exception(EntityType.CAMPAIGN, ExceptionType.ENTITY_EXCEPTION, "No more budget left");
        }

        campaignObj.setBudget(newBudget);

        repository.save(campaignObj);
        return CampaignMapper.toCampaignDto(campaignObj);
    }

}
