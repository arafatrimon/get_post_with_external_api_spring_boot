package com.example.crud_with_external_api.model;

import lombok.Data;

@Data
public class AirlineModel {
    private Long id;
    private String name;
    private String country;
    private String logo;
    private String slogan;
    private String head_quaters;
    private String website;
    private String established;
}
