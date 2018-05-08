package com.example.vaadin.nba.backend.data.entity.widget;
import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "uid",
        "title",
        "deep_link",
        "last_updated",
        "count",
        "categories"
})
public class Widget extends JsonEntity {

    @JsonProperty("uid")
    private String uid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("deep_link")
    private String deepLink;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("items")
    private List<Item> categories = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Widget() {
    }

    /**
     *
     * @param uid
     * @param title
     * @param count
     * @param categories
     * @param deepLink
     * @param lastUpdated
     */
    public Widget(String uid, String title, String deepLink, String lastUpdated, Integer count, List<Item> categories) {
        super();
        this.uid = uid;
        this.title = title;
        this.deepLink = deepLink;
        this.lastUpdated = lastUpdated;
        this.count = count;
        this.categories = categories;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("deep_link")
    public String getDeepLink() {
        return deepLink;
    }

    @JsonProperty("deep_link")
    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    @JsonProperty("last_updated")
    public String getLastUpdated() {
        return lastUpdated;
    }

    @JsonProperty("last_updated")
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("items")
    public List<Item> getCategories() {
        return categories;
    }

    @JsonProperty("items")
    public void setCategories(List<Item> categories) {
        this.categories = categories;
    }

}