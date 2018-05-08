package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.app.HasLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.annotation.PostConstruct;
import java.util.Collections;

public abstract class RequestService implements HasLogger {

    protected HttpHeaders headers;
    protected HttpEntity<String> entity;

    @PostConstruct
    public void init() {
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        headers.set("Accept-Language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7");
        entity = new HttpEntity<>(headers);
    }
}
