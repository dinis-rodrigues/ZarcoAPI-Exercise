package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.dto.model.CampaignDto;

import java.util.List;

public interface ICampaignService {
    List<CampaignDto> getAllCampaigns();
    CampaignDto getCampaignById(String id);
    CampaignDto consumeBudget(String id, double amount);
}
