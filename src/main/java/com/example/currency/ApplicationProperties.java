package com.example.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationProperties {

    @Autowired
    private Environment env;

    public String getProperty(String key){
        return env.getProperty(key);
    }

}
