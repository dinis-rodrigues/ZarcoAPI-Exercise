package com.example.ZarcoAPI;

import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.model.Merchant;
import com.example.ZarcoAPI.repository.ICampaignRepository;
import com.example.ZarcoAPI.repository.IMerchantRepository;
import com.example.ZarcoAPI.util.FakeDataUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ZarcoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZarcoApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(IMerchantRepository merchantRepository, ICampaignRepository campaignRepository){
		return args -> {

			List<Merchant> merchants = merchantRepository.findAll();
			if(merchants.isEmpty()){
				// Add merchants to DB
				List<Merchant> newMerchants = FakeDataUtils.generateMerchants();
				merchantRepository.saveAll(newMerchants);
			}

			List<Campaign> campaigns = campaignRepository.findAll();
			if(campaigns.isEmpty()){
				// Add merchants to DB
				List<Campaign> newCampaigns = FakeDataUtils.generateCampaigns();
				campaignRepository.saveAll(newCampaigns);
			}
		};
	}
}
