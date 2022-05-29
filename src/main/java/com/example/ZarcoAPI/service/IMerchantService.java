package com.example.ZarcoAPI.service;

import com.example.ZarcoAPI.dto.model.MerchantDto;

import java.util.List;

public interface IMerchantService {
    MerchantDto getMerchantById(String id);
    List<MerchantDto> getAllMerchants();
}
