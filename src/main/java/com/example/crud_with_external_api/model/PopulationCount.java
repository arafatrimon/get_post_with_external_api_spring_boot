package com.example.crud_with_external_api.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PopulationCount {

    private String year;

    private String value;

    private String sex;

    private String reliabilty;

    private Map<String,Object> additionalProperties=new HashMap<>();
}
