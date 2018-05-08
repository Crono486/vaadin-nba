package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.entity.widget.Widget;
import com.example.vaadin.nba.backend.data.enumeration.WidgetsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WidgetsService extends RequestService {

    private final RestTemplate restTemplate;

    @Autowired
    public WidgetsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Widget getWidgets(final WidgetsEndpoint endpoint) {
        String requestUrl = endpoint.toString();
        requestUrl += ".json";
        Widget result = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Widget.class).getBody();
        String logMessage = "Requested uid: " + result.getUid();
        getLogger().info(logMessage);
        return result;
    }
}
