package com.example.currency.api_clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://openexchangerates.org/api/", name = "Currency")
public interface CurrencyApiClient {

    // getting current rates of base currency & currency which is given by user in GET request
    /**
     * @param id - API key
     * @return JSON string
     **/
    @GetMapping("latest.json?app_id={id}&symbols={baseCurrency}%2C{currency2}")
    String getTodayRates(@PathVariable String id, @PathVariable String baseCurrency,
                                @PathVariable String currency2);

    // getting historical rates of base currency & currency which is given by user in GET request
    /**
     * @param yesterday - day in string format "yyyy-MM-dd"
     * @param id - API key
     * @return JSON string
     **/
    @GetMapping("historical/{yesterday}.json?app_id={id}&" +
            "symbols={baseCurrency}%2C{currency2}")
    String getYesterdayRates(@PathVariable String yesterday, @PathVariable String id,
                             @PathVariable String baseCurrency, @PathVariable String currency2);
}
