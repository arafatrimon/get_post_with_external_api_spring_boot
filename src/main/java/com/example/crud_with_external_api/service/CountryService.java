package com.example.crud_with_external_api.service;

import com.example.crud_with_external_api.model.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import reactor.core.publisher.Flux;

@Service

public class CountryService {

    @Autowired
    WebClient webClient;

    private static final Logger logger= LogManager.getLogger(CountryService.class);

    public Flux<DataModel> getCitiesRetrieve(){
        return webClient
                .get()
                .uri("https://countriesnow.space/api/v0.1/countries/population/cities")
                .retrieve()
                .onStatus(HttpStatus::isError,response->{
                    return null;
                })
                .bodyToFlux(DataModel.class)
                .doOnError(throwable -> {logger.error("Failed for some reason",throwable);});


    }
}
