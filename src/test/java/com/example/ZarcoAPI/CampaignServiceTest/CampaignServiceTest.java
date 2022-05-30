package com.example.ZarcoAPI.CampaignServiceTest;

import com.example.ZarcoAPI.dto.mapper.CampaignMapper;
import com.example.ZarcoAPI.dto.model.CampaignDto;
import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.repository.ICampaignRepository;
import com.example.ZarcoAPI.service.CampaignService;
import com.example.ZarcoAPI.util.FakeDataUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CampaignServiceTest {
    private CampaignService campaignService;
    private MongoTemplate mongoTemplate;
    private List<CampaignDto> campaignsData;
    @Autowired
    ICampaignRepository campaignRepository;

    @Before
    public void setup(){
        // Populate database
        List<Campaign> campaigns = campaignRepository.findAll();
        campaignsData = campaigns
                .stream()
                .map(CampaignMapper::toCampaignDto)
                .collect(Collectors.toList());
        if(campaigns.isEmpty()){
            // Add merchants to DB
            List<Campaign> newCampaigns = FakeDataUtils.generateCampaigns();
            campaignRepository.saveAll(newCampaigns);
            // Save Dto campaigns
            campaignsData = newCampaigns
                    .stream()
                    .map(CampaignMapper::toCampaignDto)
                    .collect(Collectors.toList());
        }

        campaignService = new CampaignService(campaignRepository, mongoTemplate);
    }

    @Test
    public void shouldReturnAllDocuments(){
        // Act
        List<CampaignDto> campaigns = campaignService.getAllCampaigns();
        // Assert
        assertEquals(campaignsData, campaigns);
    }
}
