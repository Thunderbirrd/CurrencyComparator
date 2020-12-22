package com.example.currency.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    @GetMapping("/{id}")
    public void compareToBaseCurrency(@PathVariable Integer id){

    }
}
