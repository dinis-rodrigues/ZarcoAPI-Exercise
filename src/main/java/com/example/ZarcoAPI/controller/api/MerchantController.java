package com.example.ZarcoAPI.controller.api;

import com.example.ZarcoAPI.dto.httpresponse.HttpResponse;
import com.example.ZarcoAPI.dto.model.MerchantDto;
import com.example.ZarcoAPI.service.CampaignService;
import com.example.ZarcoAPI.service.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/merchants")
public class MerchantController {

    @Autowired
    private final MerchantService merchantService;

    @GetMapping
    public HttpResponse getAllMerchants(){
        try {
            return HttpResponse.ok().setPayload(merchantService.getAllMerchants());
        } catch (Exception e) {
            return HttpResponse.exception().setErrors("An error occurred during your request");
        }
    }

    @GetMapping("/{id}")
    public HttpResponse getMerchantById(@PathVariable("id") Optional<String> id){
        try {
            return HttpResponse.ok().setPayload(merchantService.getMerchantById(id.get()));

        } catch (Exception e) {
            return HttpResponse.badRequest().setErrors("Merchant not found");
        }
    }

}
