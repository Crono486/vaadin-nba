package com.example.vaadin.nba.backend.data.enumeration;

import com.example.vaadin.nba.backend.util.Constants;

public enum WidgetsEndpoint {
    home_daily,
    home_playoffs,
    home_season;

    @Override
    public String toString() {
        return Constants.ENDPOINT_WIDGETS + this.name();
    }
}
