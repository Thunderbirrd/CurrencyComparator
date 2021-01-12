package com.example.currency.controllers;

import com.example.currency.ApplicationProperties;
import com.example.currency.api_clients.CurrencyApiClient;
import com.example.currency.api_clients.GifApiClient;
import com.example.currency.config.TestConfig;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
class CurrencyControllerTest {
    @Autowired
    CurrencyController currencyController;

    // mocked dependencies
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    CurrencyApiClient currencyApiClient;
    @Autowired
    GifApiClient gifApiClient;

    private static final Date yesterday = new Date(new Date().getTime() - (1000 * 60 * 60 * 24));
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    void getYesterdayDateString() throws ParseException {
        assertEquals(formatter.format(yesterday),
                currencyController.getYesterdayDateString());
    }

    private final Random random = new Random();
    private final double rate1 = random.nextDouble();
    private final double rate2 = random.nextDouble();
    private final String jsonString = "{\n" +
            "    \"disclaimer\": \"Usage subject to terms: https://openexchangerates.org/terms\",\n" +
            "    \"license\": \"https://openexchangerates.org/license\",\n" +
            "    \"timestamp\": 1610449200,\n" +
            "    \"base\": \"USD\",\n" +
            "    \"rates\": {\n" +
            "        \"EUR\": " + rate1 + ",\n" +
            "        \"RUB\": " + rate2 + ",\n" +
            "    }\n" +
            "}";
    private final JSONObject jsonTodayRates = new JSONObject(jsonString);
    @Test
    void getCurrencyRateInBaseCurrency() {
        assertEquals(currencyController.getCurrencyRateInBaseCurrency(jsonTodayRates,
                "RUB", "EUR"), rate2 / rate1);
    }
}