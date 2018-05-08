package com.example.vaadin.nba.backend.data.enumeration;

public enum SeasonType {
    Regular_Season("Regular Season"),
    Pre_Season("Pre Season"),
    Playoffs("Playoffs");

    private String value;

    SeasonType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
