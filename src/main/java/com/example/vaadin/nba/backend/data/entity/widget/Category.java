package com.example.vaadin.nba.backend.data.entity.widget;


import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title",
        "deep_link",
        "name",
        "timestamp",
        "season",
        "seasontype",
        "gamedate",
        "leagueid",
        "urldate",
        "permode",
        "playerstats",
        "teamstats",
        "livegamestate"
})
public class Category extends JsonEntity {

    @JsonProperty("title")
    private String title;
    @JsonProperty("deep_link")
    private String deepLink;
    @JsonProperty("name")
    private String name;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("season")
    private String season;
    @JsonProperty("seasontype")
    private String seasontype;
    @JsonProperty("gamedate")
    private String gamedate;
    @JsonProperty("leagueid")
    private String leagueid;
    @JsonProperty("urldate")
    private String urldate;
    @JsonProperty("permode")
    private String permode;
    @JsonProperty("playerstats")
    private List<PlayerStat> playerstats = null;
    @JsonProperty("teamstats")
    private List<TeamStat> teamstats = null;
    @JsonProperty("livegamestate")
    private List<LiveGameState> livegamestate = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public Category() {
    }

    /**
     *
     * @param timestamp
     * @param urldate
     * @param title
     * @param permode
     * @param season
     * @param seasontype
     * @param deepLink
     * @param name
     * @param livegamestate
     * @param leagueid
     * @param gamedate
     * @param teamstats
     * @param playerstats
     */
    public Category(String title, String deepLink, String name, String timestamp, String season, String seasontype, String gamedate, String leagueid, String urldate, String permode, List<PlayerStat> playerstats, List<TeamStat> teamstats, List<LiveGameState> livegamestate) {
        super();
        this.title = title;
        this.deepLink = deepLink;
        this.name = name;
        this.timestamp = timestamp;
        this.season = season;
        this.seasontype = seasontype;
        this.gamedate = gamedate;
        this.leagueid = leagueid;
        this.urldate = urldate;
        this.permode = permode;
        this.playerstats = playerstats;
        this.teamstats = teamstats;
        this.livegamestate = livegamestate;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("deep_link")
    public String getDeepLink() {
        return deepLink;
    }

    @JsonProperty("deep_link")
    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("season")
    public String getSeason() {
        return season;
    }

    @JsonProperty("season")
    public void setSeason(String season) {
        this.season = season;
    }

    @JsonProperty("seasontype")
    public String getSeasontype() {
        return seasontype;
    }

    @JsonProperty("seasontype")
    public void setSeasontype(String seasontype) {
        this.seasontype = seasontype;
    }

    @JsonProperty("gamedate")
    public String getGamedate() {
        return gamedate;
    }

    @JsonProperty("gamedate")
    public void setGamedate(String gamedate) {
        this.gamedate = gamedate;
    }

    @JsonProperty("leagueid")
    public String getLeagueid() {
        return leagueid;
    }

    @JsonProperty("leagueid")
    public void setLeagueid(String leagueid) {
        this.leagueid = leagueid;
    }

    @JsonProperty("urldate")
    public String getUrldate() {
        return urldate;
    }

    @JsonProperty("urldate")
    public void setUrldate(String urldate) {
        this.urldate = urldate;
    }

    @JsonProperty("permode")
    public String getPermode() {
        return permode;
    }

    @JsonProperty("permode")
    public void setPermode(String permode) {
        this.permode = permode;
    }

    @JsonProperty("playerstats")
    public List<PlayerStat> getPlayerstats() {
        return playerstats;
    }

    @JsonProperty("playerstats")
    public void setPlayerstats(List<PlayerStat> playerstats) {
        this.playerstats = playerstats;
    }

    @JsonProperty("teamstats")
    public List<TeamStat> getTeamstats() {
        return teamstats;
    }

    @JsonProperty("teamstats")
    public void setTeamstats(List<TeamStat> teamstats) {
        this.teamstats = teamstats;
    }

    @JsonProperty("livegamestate")
    public List<LiveGameState> getLivegamestate() {
        return livegamestate;
    }

    @JsonProperty("livegamestate")
    public void setLivegamestate(List<LiveGameState> livegamestate) {
        this.livegamestate = livegamestate;
    }
}