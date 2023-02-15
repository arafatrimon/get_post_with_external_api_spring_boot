package com.example.crud_with_external_api.controller;

import com.example.crud_with_external_api.model.AirlineModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("rest")
public class UsingRestTemplateController {
    @Autowired
    RestTemplate restTemplate;
    private static final Logger logger = LogManager.getLogger(UsingFluxWebClientController.class);


    @GetMapping("/restCountry")
    public ResponseEntity<?> getCountries(){
        try {
            String uri="https://countriesnow.space/api/v0.1/countries/population/cities";
            String result=restTemplate.getForObject(uri,String.class);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error!,Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/airline_rest")
    public ResponseEntity<?> createAirlineRest(@RequestBody AirlineModel body){
        try {
            String uri="https://api.instantwebtools.net/v1/airlines";
            ResponseEntity<String> result=restTemplate.postForEntity(uri,body,String.class);
            return new ResponseEntity<>(result.getStatusCodeValue()==200 ? "Airline created successfully":"Airline not create successfully", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error !, Please try again ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
