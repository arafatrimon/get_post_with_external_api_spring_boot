package com.example.crud_with_external_api.controller;

import com.example.crud_with_external_api.model.AirlineModel;
import com.example.crud_with_external_api.util.ConvertToJson;
import com.example.crud_with_external_api.util.HttpRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("http")
public class UsingHttpRequestController {

    @Autowired
    RestTemplate restTemplate;
    private static final Logger logger = LogManager.getLogger(UsingFluxWebClientController.class);

    ///Get value from external api using custom HttpRequest

    @GetMapping
    public ResponseEntity<?> getCountry(){
        try {
            HttpRequest request=HttpRequest
                    .get("https://countriesnow.space/api/v0.1/countries/population/cities")
                    .connectTimeout(12000);
            String res =request.body();
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error !, Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/airline_http")
    public ResponseEntity<?> createAirlineHttp(@RequestBody AirlineModel body){
        try {
            String uri="https://api.instantwebtools.net/v1/airlines";
            logger.info("REQUEST_BODY :"+ ConvertToJson.setJsonString(body));
            HttpRequest results=HttpRequest.post(uri)
                    .header("Content-Type","application/json")
                    .send(ConvertToJson.setJsonString(body));

            int status =results.code();

            logger.info("STATUS CODE :"+status);

            return new ResponseEntity<>(status==200? "Airline created successfully":"Airline not created successfully",status==200? HttpStatus.OK:HttpStatus.BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error,Please try again",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
