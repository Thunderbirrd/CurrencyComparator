package com.example.currency.controllers;

import com.example.currency.ApplicationProperties;
import com.example.currency.api_clients.CurrencyApiClient;
import com.example.currency.api_clients.GifApiClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@RestController
public class CurrencyController {

    @Autowired
    ApplicationProperties properties;

    @Autowired
    CurrencyApiClient currencyApiClient;

    @Autowired
    GifApiClient gifApiClient;

    @GetMapping("/{currencyID}")
    public BufferedImage compareToBaseCurrency(@PathVariable String currencyID) throws ParseException, IOException {
        String currencyApiKey = properties.getProperty("currency-api-key");
        String baseCurrency = properties.getProperty("base-currency");
        String gifApiKey = properties.getProperty("gif-api-key");
        String yesterday = getYesterdayDateString();
        String jsonStringTodayRates = currencyApiClient.getTodayRates(currencyApiKey, baseCurrency, currencyID);
        double todayRate = getCurrencyRateInBaseCurrency(
                new JSONObject(jsonStringTodayRates), baseCurrency, currencyID);

        String jsonStringYesterdayRates = currencyApiClient.getYesterdayRates(
                yesterday, currencyApiKey, baseCurrency, currencyID);
        double yesterdayRate = getCurrencyRateInBaseCurrency(
                new JSONObject(jsonStringYesterdayRates), baseCurrency, currencyID);

        String jsonGIFString;
        if(todayRate >= yesterdayRate){
            jsonGIFString = gifApiClient.getGifJSON(gifApiKey, "rich", new Random().nextInt(10));
            JSONObject jsonGIF = new JSONObject(jsonGIFString);
            JSONArray data = jsonGIF.getJSONArray("data");
            String id = data.getJSONObject(0).getString("id");
        }else{
            jsonGIFString = gifApiClient.getGifJSON(gifApiKey, "broke", new Random().nextInt(10));
            JSONObject jsonGIF = new JSONObject(jsonGIFString);
            JSONArray data = jsonGIF.getJSONArray("data");
            String id = data.getJSONObject(0).getString("id");
        }
        return getGIFObject(jsonGIFString);
    }

    private BufferedImage getGIFObject(String jsonGIFString) throws IOException {
        JSONObject jsonGIF = new JSONObject(jsonGIFString);
        JSONArray data = jsonGIF.getJSONArray("data");
        String id = data.getJSONObject(0).getString("id");
        String urlString = "https://i.giphy.com/media/" + id + "/giphy.gif";
        URL url = new URL(urlString);
        return ImageIO.read(url);
    }

    @GetMapping(value = "/gif", produces = "image/gif")
    public @ResponseBody BufferedImage getGif() throws IOException {
        String gifApiKey = properties.getProperty("gif-api-key");
        String jsonGIFString = gifApiClient.getGifJSON(gifApiKey, "rich", new Random().nextInt(10));
        return getGIFObject(jsonGIFString);
    }

    public String getYesterdayDateString() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String yesterday = formatter.format(date); //getting TODAY date string
        int day = Integer.parseInt(yesterday.substring(yesterday.length() - 2)) - 1;
        yesterday = yesterday.substring(0, yesterday.length() - 2) + day; //getting yesterday date string
        date = formatter.parse(yesterday);
        return formatter.format(date);
    }

    public double getCurrencyRateInBaseCurrency(JSONObject jsonTodayRates, String baseCurrency, String currency2){
        // Strange bug: program somehow makes third call of this function(when there are only 2 in my code)
        // with currency2 = "favicon.ico"
        if(!currency2.equals("favicon.ico")) {
            JSONObject rates = jsonTodayRates.getJSONObject("rates");
            double baseRate = rates.getDouble(baseCurrency);
            double currency2Rate = rates.getDouble(currency2);
            return baseRate / currency2Rate;
        }
        return 0.0;
    }
}
