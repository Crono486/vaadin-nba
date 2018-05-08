package com.example.vaadin.nba.backend.data;

import java.util.Map;

public class PlayerProfileV2 {

    public Map<String, String> values;

    public String getPlayerId() {
        return values.get("PLAYER_ID");
    }

    public String getSeasonId() {
        return values.get("SEASON_ID");
    }

    public String getLeagueId() {
        return values.get("LEAGUE_ID");
    }

    public String getTeamId() {
        return values.get("TEAM_ID");
    }

    public String getTeamAbbreviation() {
        return values.get("TEAM_ABBREVIATION");
    }

    public String getPlayerAge() {
        return values.get("PLAYER_AGE");
    }

    public String getGamesPlayed() {
        return values.get("GP");
    }

    public String getGamesStarted() {
        return values.get("GS");
    }

    public String getMinutes() {
        return values.get("MIN");
    }

    public String getFieldGoalMade() {
        return values.get("FGM");
    }

    public String getFieldGoalAttempts() {
        return values.get("FGA");
    }

    public String getFieldGoalPercentage() {
        return values.get("FG_PCT");
    }

    public String getThreePointerMade() {
        return values.get("FG3M");
    }

    public String getThreePointerAttempts() {
        return values.get("FG3A");
    }

    public String getThreePointerPercentage() {
        return values.get("FG3_PCT");
    }

    public String getFreeThrowMade() {
        return values.get("FTM");
    }

    public String getFreeThrowAttempts() {
        return values.get("FTA");
    }

    public String getFreeThrowPercentage() {
        return values.get("FT_PCT");
    }

    public String getOffensiveRebounds() {
        return values.get("OREB");
    }

    public String getDefensiveRebounds() {
        return values.get("DREB");
    }

    public String getRebounds() {
        return values.get("REB");
    }

    public String getAssists() {
        return values.get("AST");
    }

    public String getSteals() {
        return values.get("STL");
    }

    public String getBlocks() {
        return values.get("BLK");
    }

    public String getTurnover() {
        return values.get("TOV");
    }

    public String getPersonalFouls() {
        return values.get("PF");
    }

    public String getPoints() {
        return values.get("PTS");
    }
}
