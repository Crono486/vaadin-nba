package com.example.vaadin.nba.backend.data.entity.stats;

import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "resource",
        "resultSets"
})
public class Stats extends JsonEntity {

    @JsonProperty("resource")
    private String resource;
    @JsonProperty("resultSets")
    private List<ResultSet> resultSets = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Stats() {
    }

    /**
     *
     * @param resultSets
     * @param resource
     */
    public Stats(String resource, List<ResultSet> resultSets) {
        super();
        this.resource = resource;
        this.resultSets = resultSets;
    }

    @JsonProperty("resource")
    public String getResource() {
        return resource;
    }

    @JsonProperty("resource")
    public void setResource(String resource) {
        this.resource = resource;
    }

    @JsonProperty("resultSets")
    public List<ResultSet> getResultSets() {
        return resultSets;
    }

    @JsonProperty("resultSets")
    public void setResultSets(List<ResultSet> resultSets) {
        this.resultSets = resultSets;
    }
}