package com.example.crud_with_external_api.controller;


import com.example.crud_with_external_api.model.AirlineModel;
import com.example.crud_with_external_api.model.DataModel;
import com.example.crud_with_external_api.service.CountryService;
import com.example.crud_with_external_api.util.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/country")
public class UsingFluxWebClientController {

    @Autowired
    CountryService countryService;
    @GetMapping("/web_client_country")
    public Flux<DataModel> getCountry1(){
        try{
            return countryService.getCitiesRetrieve();
        }catch (Exception e){
            e.printStackTrace();
            return Flux.error(new Exception("server error"));
        }
    }






}
