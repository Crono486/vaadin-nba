package com.example.vaadin.nba.backend.data.enumeration;

public enum LeagueID {
    NBA("00"),
    ABA("01");

    private String value;

    LeagueID(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
