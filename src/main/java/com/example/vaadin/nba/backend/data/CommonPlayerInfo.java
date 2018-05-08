package com.example.vaadin.nba.backend.data;

import java.util.HashMap;
import java.util.Map;

public class CommonPlayerInfo {

    public Map<String, String> values;

    public CommonPlayerInfo() {
        values = new HashMap<>();
    }

    public String getPersonId() {
        return values.get("PERSON_ID");
    }

    public String getFirstName() {
        return values.get("FIRST_NAME");
    }

    public String getLastName() {
        return values.get("LAST_NAME");
    }

    public String getDisplayFirstLast() {
        return values.get("DISPLAY_FIRST_LAST");
    }

    public String getDisplayLastCommaFirst() {
        return values.get("DISPLAY_LAST_COMMA_FIRST");
    }

    public String getDisplayFiLast() {
        return values.get("DISPLAY_FI_LAST");
    }

    public String getBirthDate() {
        return values.get("BIRTHDATE");
    }

    public String getSchool() {
        return values.get("SCHOOL");
    }

    public String getCountry() {
        return values.get("COUNTRY");
    }

    public String getLastAffiliation() {
        return values.get("LAST_AFFILIATION");
    }

    public String getHeight() {
        return values.get("HEIGHT");
    }

    public String getWeight() {
        return values.get("WEIGHT");
    }

    public String getSeasonExp() {
        return values.get("SEASON_EXP");
    }

    public String getJersey() {
        return values.get("JERSEY");
    }

    public String getPosition() {
        return values.get("POSITION");
    }

    public String getRosterStatus() {
        return values.get("ROSTERSTATUS");
    }

    public String getTeamId() {
        return values.get("TEAM_ID");
    }

    public String getTeamName() {
        return values.get("TEAM_NAME");
    }

    public String getTeamAbbreviation() {
        return values.get("TEAM_ABBREVIATION");
    }

    public String getTeamCode() {
        return values.get("TEAM_CODE");
    }

    public String getTeamCity() {
        return values.get("TEAM_CITY");
    }

    public String getPlayerCode() {
        return values.get("PLAYERCODE");
    }

    public String getFromYear() {
        return values.get("FROM_YEAR");
    }

    public String getToYear() {
        return values.get("TO_YEAR");
    }

    public String getDLeagueFlag() {
        return values.get("DLEAGUE_FLAG");
    }

    public String getGamesPlayedFlag() {
        return values.get("GAMES_PLAYED_FLAG");
    }

    public String getDraftYear() {
        return values.get("DRAFT_YEAR");
    }

    public String getDraftRound() {
        return values.get("DRAFT_ROUND");
    }

    public String getDraftNumber() {
        return values.get("DRAFT_NUMBER");
    }

    public String getHeadlinePts() {
        return values.get("PTS");
    }

    public String getHeadlineReb() {
        return values.get("REB");
    }

    public String getHeadlineAst() {
        return values.get("AST");
    }

    public String getHeadlinePie() {
        return values.get("PIE");
    }
}
