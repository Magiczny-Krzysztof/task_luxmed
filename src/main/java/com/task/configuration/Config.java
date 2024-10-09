package com.task.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@org.springframework.context.annotation.Configuration
public class Config {

    @Bean
    public HttpMessageConverter<Object> jsonConverter() {
        return new MappingJackson2HttpMessageConverter();
    }

}
