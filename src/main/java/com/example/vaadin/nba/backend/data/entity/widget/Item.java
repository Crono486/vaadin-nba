package com.example.vaadin.nba.backend.data.entity.widget;

import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "deep_link",
        "uid",
        "title",
        "template",
        "last_updated",
        "count",
        "items"
})
public class Item extends JsonEntity {

    @JsonProperty("deep_link")
    private String deepLink;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("title")
    private String title;
    @JsonProperty("template")
    private String template;
    @JsonProperty("last_updated")
    private String lastUpdated;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("items")
    private List<Category> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Item() {
    }

    /**
     *
     * @param template
     * @param uid
     * @param title
     * @param count
     * @param items
     * @param deepLink
     * @param lastUpdated
     */
    public Item(String deepLink, String uid, String title, String template, String lastUpdated, Integer count, List<Category> items) {
        super();
        this.deepLink = deepLink;
        this.uid = uid;
        this.title = title;
        this.template = template;
        this.lastUpdated = lastUpdated;
        this.count = count;
        this.items = items;
    }

    @JsonProperty("deep_link")
    public String getDeepLink() {
        return deepLink;
    }

    @JsonProperty("deep_link")
    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
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

    @JsonProperty("template")
    public String getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(String template) {
        this.template = template;
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
    public List<Category> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<Category> items) {
        this.items = items;
    }
}