package com.example.vaadin.nba.backend.data;

import java.util.Map;

public class DraftHistory {

    public Map<String, String> values;
    public int rowIndex;

    public String getPersonId() {
        return values.get("PERSON_ID");
    }

    public String getPlayerName() {
        return values.get("PLAYER_NAME");
    }

    public String getSeason() {
        return values.get("SEASON");
    }

    public Integer getRoundNumber() {
        return Integer.valueOf(values.get("ROUND_NUMBER"));
    }

    public Integer getRoundPick() {
        return Integer.valueOf(values.get("ROUND_PICK"));
    }

    public Integer getOverallPick() {
        return Integer.valueOf(values.get("OVERALL_PICK"));
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

    public String getTeamAbbreviation() {
        return values.get("TEAM_ABBREVIATION");
    }

    public String getOrganization() {
        return values.get("ORGANIZATION");
    }

    public String getOrganizationType() {
        return values.get("ORGANIZATION_TYPE");
    }
}
