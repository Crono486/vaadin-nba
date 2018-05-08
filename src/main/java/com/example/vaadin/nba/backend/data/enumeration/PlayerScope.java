package com.example.vaadin.nba.backend.data.enumeration;

public enum PlayerScope {
    All_Players("All Players"),
    Rookies("Rookies");

    private String value;

    PlayerScope(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
