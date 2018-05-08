package com.example.vaadin.nba.backend.data;

import java.util.Map;

public class Franchise {

    public Map<String, String> values;

    public String getLeagueId() {
        return values.get("LEAGUE_ID");
    }

    public String getTeamId() {
        return values.get("TEAM_ID");
    }

    public String getFranchiseName() {
        return getTeamCity() + " " + getTeamName();
    }

    public String getTeamCity() {
        return values.get("TEAM_CITY");
    }

    public String getTeamName() {
        return values.get("TEAM_NAME");
    }

    public String getStartYear() {
        return values.get("START_YEAR");
    }

    public String getEndYear() {
        return values.get("END_YEAR");
    }

    public String getYears() {
        return values.get("YEARS");
    }

    public Integer getGames() {
        return Integer.valueOf(values.get("GAMES"));
    }

    public Integer getWins() {
        return Integer.valueOf(values.get("WINS"));
    }

    public Integer getLosses() {
        return Integer.valueOf(values.get("LOSSES"));
    }

    public Double getWinPercentage() {
        return Double.valueOf(values.get("WIN_PCT"));
    }

    public Integer getPlayoffAppearances() {
        return Integer.valueOf(values.get("PO_APPEARANCES"));
    }

    public Integer getDivisionTitles() {
        return Integer.valueOf(values.get("DIV_TITLES"));
    }

    public Integer getConferenceTitles() {
        return Integer.valueOf(values.get("CONF_TITLES"));
    }

    public Integer getLeagueTitles() {
        return Integer.valueOf(values.get("LEAGUE_TITLES"));
    }

    public Boolean isMainDataSet() {
        return Boolean.valueOf(values.get("IsMainDataSet"));
    }
}
