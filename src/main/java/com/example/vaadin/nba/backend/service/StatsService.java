package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;
import com.example.vaadin.nba.backend.data.entity.stats.Stats;
import com.example.vaadin.nba.backend.data.enumeration.StatsEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class StatsService extends RequestService {

    private final RestTemplate restTemplate;

    @Autowired
    public StatsService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<ResultSet> getStats(StatsEndpoint endpoint, String... parameter) {
        String requestUrl = endpoint.toString();
        if(parameter.length > 0) {
            requestUrl += "/?";
            requestUrl += parameter[0];
            for(int i = 1; i < parameter.length; i++) {
                requestUrl += "&";
                requestUrl += parameter[i];
            }
        }
        Stats result = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, Stats.class).getBody();
        String logMessage = "Requested resource: " + result.getResource();
        Map<String, Object> additionalProperties = result.getAdditionalProperties();
        Set<String> parameterKeys = additionalProperties.keySet();
        Iterator<String> iterator = parameterKeys.iterator();
        logMessage += " (";
        while(iterator.hasNext()) {
            String key = iterator.next();
            logMessage += key + ": " + additionalProperties.get(key);
            if(iterator.hasNext()) {
                logMessage += ", ";
            }
        }
        logMessage += ")";
        getLogger().info(logMessage);
        return result.getResultSets();
    }
}
