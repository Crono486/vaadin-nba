package com.example.vaadin.nba.backend.data.entity.widget;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "GAME_ID",
        "TEAM_ID",
        "TEAM_CITY",
        "TEAM_NAME",
        "TEAM_ABBREVIATION",
        "URL_DATE",
        "URL_TEAMS",
        "PTS",
        "REB",
        "AST",
        "BLK",
        "STL",
        "FG_PCT",
        "FG3M",
        "FG3_PCT",
        "FT_PCT"
})
public class TeamStat extends Stat {

    @JsonProperty("TEAM_CITY")
    private String team_city;
    @JsonProperty("TEAM_NAME")
    private String team_name;

    /**
     * No args constructor for use in serialization
     *
     */
    public TeamStat() {
    }

    /**
     *
     * @param game_id
     * @param team_id
     * @param team_city
     * @param team_name
     * @param team_abbreviation
     * @param url_date
     * @param url_teams
     * @param pts
     * @param reb
     * @param ast
     * @param blk
     * @param stl
     * @param tov
     * @param fg_pct
     * @param fg3m
     * @param fg3_pct
     * @param ftm
     * @param ft_pct
     * @param nba_fantasy_pts
     * @param fantasy_points
     */
    public TeamStat(String game_id, Integer team_id, String team_city, String team_name, String team_abbreviation, String url_date, String url_teams, Double pts, Double reb, Double ast, Double blk, Double stl, Double tov, Double fg_pct, Double fg3m, Double fg3_pct, Double ftm, Double ft_pct, Double nba_fantasy_pts, Double fantasy_points) {
        super(game_id, team_id, team_abbreviation, url_date, url_teams, pts, reb, ast, blk, stl, tov, fg_pct, fg3m, fg3_pct, ftm, ft_pct, nba_fantasy_pts, fantasy_points);
        this.team_city = team_city;
        this.team_name = team_name;
    }

    @JsonProperty("TEAM_CITY")
    public String getTeam_city() {
        return team_city;
    }

    @JsonProperty("TEAM_CITY")
    public void setTeam_city(String team_city) {
        this.team_city = team_city;
    }

    @JsonProperty("TEAM_NAME")
    public String getTeam_name() {
        return team_name;
    }

    @JsonProperty("TEAM_NAME")
    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam() {
        return team_city + " " + team_name;
    }
}