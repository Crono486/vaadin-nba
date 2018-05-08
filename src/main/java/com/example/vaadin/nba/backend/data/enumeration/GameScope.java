package com.example.vaadin.nba.backend.data.enumeration;

public enum GameScope {
    Season("Season"),
    Last_10("Last 10"),
    Yesterday("Yesterday"),
    Finals("Finals");

    private String value;

    GameScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
