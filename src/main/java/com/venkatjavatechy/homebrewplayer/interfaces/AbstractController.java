package com.venkatjavatechy.homebrewplayer.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

public class AbstractController {
    protected static final Logger logger = LoggerFactory.getLogger(AbstractController.class);

    @Value("${homebrew.formula.api.url}")
    protected String formulaUrl;

    @Autowired
    protected RestTemplate restTemplate;

    @Autowired
    protected RetryTemplate retryTemplate;

    protected HttpEntity<String> buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return entity;
    }

}
