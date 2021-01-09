package com.example.currency.api_clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "api.giphy.com/v1/gifs/search/", name = "GIFs")
public interface GifApiClient {
}
