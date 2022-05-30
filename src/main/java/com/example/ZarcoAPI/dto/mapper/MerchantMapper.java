package com.example.ZarcoAPI.dto.mapper;

import com.example.ZarcoAPI.dto.model.MerchantDto;
import com.example.ZarcoAPI.model.Merchant;

public class MerchantMapper {
    /**
     * Transforms the Merchant class object into serialized JSON data
     * @param merchant
     * @return MerchantDto
     */
    public static MerchantDto toMerchantDto(Merchant merchant) {
        return new MerchantDto()
                .setId(merchant.getId())
                .setName(merchant.getName())
                .setUrl(merchant.getUrl());
    }
}
