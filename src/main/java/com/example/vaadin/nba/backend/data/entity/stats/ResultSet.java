package com.example.vaadin.nba.backend.data.entity.stats;

import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "headers",
        "rowSet"
})
public class ResultSet extends JsonEntity {

    @JsonProperty("name")
    private String name;
    @JsonProperty("headers")
    private List<String> headers = null;
    @JsonProperty("rowSet")
    private List<List<String>> rowSet = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResultSet() {
    }

    /**
     *
     * @param headers
     * @param name
     * @param rowSet
     */
    public ResultSet(String name, List<String> headers, List<List<String>> rowSet) {
        super();
        this.name = name;
        this.headers = headers;
        this.rowSet = rowSet;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("headers")
    public List<String> getHeaders() {
        return headers;
    }

    @JsonProperty("headers")
    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    @JsonProperty("rowSet")
    public List<List<String>> getRowSet() {
        return rowSet;
    }

    @JsonProperty("rowSet")
    public void setRowSet(List<List<String>> rowSet) {
        this.rowSet = rowSet;
    }
}