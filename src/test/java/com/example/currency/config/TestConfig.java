package com.example.currency.config;

import com.example.currency.ApplicationProperties;
import com.example.currency.api_clients.CurrencyApiClient;
import com.example.currency.api_clients.GifApiClient;
import com.example.currency.controllers.CurrencyController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class TestConfig {

    @Bean
    public CurrencyController currencyController(){
        return new CurrencyController();
    }

    @Bean
    public ApplicationProperties applicationProperties(){
        return mock(ApplicationProperties.class);
    }

    @Bean
    public CurrencyApiClient currencyApiClient(){
        return mock(CurrencyApiClient.class);
    }

    @Bean
    public GifApiClient gifApiClient(){
        return mock(GifApiClient.class);
    }
}
