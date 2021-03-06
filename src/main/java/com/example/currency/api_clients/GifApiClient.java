package com.example.currency.api_clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(url = "https://api.giphy.com/v1/gifs/search", name = "GIFs")
public interface GifApiClient {

    /**
     * @param searchWord - text which will be placed in search string on GIPHY
     * @param position - position of GIF image on page
     **/
    @GetMapping("?api_key={apiKey}&q={searchWord}&limit=1&offset={position}")
    String getGifJSON(@PathVariable String apiKey,
                      @PathVariable String searchWord, @PathVariable Integer position);

}
