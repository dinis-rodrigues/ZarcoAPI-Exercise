package com.example.ZarcoAPI.controller.api;

import com.example.ZarcoAPI.dto.httpresponse.HttpResponse;
import com.example.ZarcoAPI.dto.model.CampaignDto;
import com.example.ZarcoAPI.model.Campaign;
import com.example.ZarcoAPI.service.CampaignService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;

    @GetMapping
    public HttpResponse getAllCampaigns(@RequestParam("limit") Optional<Integer> limit, @RequestParam("offset") Optional<Integer> offset){
        try {
            if(limit.isPresent() && offset.isPresent()) {
                return HttpResponse.ok().setPayload(campaignService.getCampaigns(limit.get(), offset.get()));
            } else if (limit.isPresent() && !offset.isPresent()) {
                return HttpResponse.ok().setPayload(campaignService.getCampaigns(limit.get()));
            }

            return HttpResponse.ok().setPayload(campaignService.getAllCampaigns());
        } catch (Exception e){
            return HttpResponse.badRequest().setErrors(e.getMessage());
        }

    }

    @GetMapping("/{id}")
    public HttpResponse getCampaignById(@PathVariable("id") Optional<String> id){
        try {
            return HttpResponse.ok().setPayload(campaignService.getCampaignById(id.get()));
        } catch (Exception e) {
            return HttpResponse.badRequest().setErrors(e.getMessage());
        }
    }

    @GetMapping("/{id}/consume")
    public HttpResponse consumeCampaign(@PathVariable("id") Optional<String> id, @RequestParam("amount") Optional<Double> amount){
        if(id.isPresent() && amount.isPresent()){
            try {
                CampaignDto campaign = campaignService.consumeBudget(id.get(), amount.get());
                return HttpResponse.ok().setPayload(campaign);
            } catch (Exception e) {
                return HttpResponse.badRequest().setErrors(e.getMessage());
            }

        }

        return HttpResponse.badRequest().setErrors("Missing request parameters");
    }
}
