package com.example.currency.api_clients;

import com.example.currency.ApplicationProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://openexchangerates.org/api/", name = "Currency")
public interface CurrencyApiClient {
}
