package com.example.ZarcoAPI.util;

import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.model.Merchant;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class FakeDataUtils {
    public static String getRandomName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }

    public static String getRandomUrl(){
        Faker faker = new Faker();
        return "https://" + faker.name().username() + ".com";
    }

    public static double getRandomNumber(int min, int max) {
        return Math.floor(Math.random()  * (max - min + 1) + min);
    }

    public static List<Merchant> generateMerchants() {
        List<Merchant> newMerchants = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            newMerchants.add(
                    new Merchant()
                            .setName(FakeDataUtils.getRandomName())
                            .setUrl(FakeDataUtils.getRandomUrl())
            );
        }
        return newMerchants;
    }

    public static List<Campaign> generateCampaigns() {
        List<Campaign> newCampaigns = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            newCampaigns.add(
                    new Campaign()
                            .setBudget(FakeDataUtils.getRandomNumber(0, 10000))
                            .setCashbackPercentage(0.03)
                            .setPaymentThreshold(FakeDataUtils.getRandomNumber(0, 100))
            );
        }
        return newCampaigns;
    }
}
