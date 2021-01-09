package com.example.currency.controllers;

import com.example.currency.ApplicationProperties;
import com.example.currency.api_clients.CurrencyApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    CurrencyApiClient currencyApiClient;

    @GetMapping("/{id}")
    public void compareToBaseCurrency(@PathVariable String id){
    }
}
