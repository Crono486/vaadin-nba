package com.example.vaadin.nba.backend.data.entity.widget;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "GAME_ID",
        "PLAYER_ID",
        "PLAYER_NAME",
        "TEAM_ID",
        "TEAM_ABBREVIATION",
        "URL_DATE",
        "URL_TEAMS",
        "PTS",
        "REB",
        "AST",
        "BLK",
        "STL",
        "TOV",
        "FG_PCT",
        "FG3M",
        "FG3_PCT",
        "FTM",
        "NBA_FANTASY_PTS",
        "FANTASY_POINTS"
})
public class PlayerStat extends Stat {

    @JsonProperty("PLAYER_ID")
    private Integer player_id;
    @JsonProperty("PLAYER_NAME")
    private String player_name;

    /**
     * No args constructor for use in serialization
     *
     */
    public PlayerStat() {
    }

    /**
     *
     * @param game_id
     * @param player_id
     * @param player_name
     * @param team_id
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
    public PlayerStat(String game_id, Integer player_id, String player_name, Integer team_id, String team_abbreviation, String url_date, String url_teams, Double pts, Double reb, Double ast, Double blk, Double stl, Double tov, Double fg_pct, Double fg3m, Double fg3_pct, Double ftm, Double ft_pct, Double nba_fantasy_pts, Double fantasy_points) {
        super(game_id, team_id, team_abbreviation, url_date, url_teams, pts, reb, ast, blk, stl, tov, fg_pct, fg3m, fg3_pct, ftm, ft_pct, nba_fantasy_pts, fantasy_points);
        this.player_id = player_id;
        this.player_name = player_name;
    }

    @JsonProperty("PLAYER_ID")
    public Integer getPlayer_id() {
        return player_id;
    }

    @JsonProperty("PLAYER_ID")
    public void setPlayer_id(Integer player_id) {
        this.player_id = player_id;
    }

    @JsonProperty("PLAYER_NAME")
    public String getPlayer_name() {
        return player_name;
    }

    @JsonProperty("PLAYER_NAME")
    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }
}
