package com.example.ZarcoAPI.dto.mapper;

import com.example.ZarcoAPI.dto.model.CampaignDto;
import com.example.ZarcoAPI.model.Campaign;

public class CampaignMapper {
    /**
     * Transforms the Campaign class object into serialized JSON data
     * @param campaign
     * @return CampaignDto
     */
    public static CampaignDto toCampaignDto(Campaign campaign) {
        return new CampaignDto()
                .setId(campaign.getId())
                .setBudget(campaign.getBudget())
                .setPaymentThreshold(campaign.getPaymentThreshold())
                .setCashbackPercentage(campaign.getCashbackPercentage());
    }
}
