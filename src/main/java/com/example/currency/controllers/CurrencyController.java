package com.example.currency.controllers;

import com.example.currency.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @Autowired
    ApplicationProperties properties;

    @GetMapping("/{id}")
    public void compareToBaseCurrency(@PathVariable Integer id){
    }
}
