package com.example.vaadin.nba.backend.data.entity.widget;

import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Stat extends JsonEntity {

    @JsonProperty("GAME_ID")
    private String game_id;
    @JsonProperty("TEAM_ID")
    private Integer team_id;
    @JsonProperty("TEAM_ABBREVIATION")
    private String team_abbreviation;
    @JsonProperty("URL_DATE")
    private String url_date;
    @JsonProperty("URL_TEAMS")
    private String url_teams;
    @JsonProperty("PTS")
    private Double pts;
    @JsonProperty("REB")
    private Double reb;
    @JsonProperty("AST")
    private Double ast;
    @JsonProperty("BLK")
    private Double blk;
    @JsonProperty("STL")
    private Double stl;
    @JsonProperty("TOV")
    private Double tov;
    @JsonProperty("FG_PCT")
    private Double fg_pct;
    @JsonProperty("FG3M")
    private Double fg3m;
    @JsonProperty("FG3_PCT")
    private Double fg3_pct;
    @JsonProperty("FTM")
    private Double ftm;
    @JsonProperty("FT_PCT")
    private Double ft_pct;
    @JsonProperty("NBA_FANTASY_PTS")
    private Double nba_fantasy_pts;
    @JsonProperty("FANTASY_POINTS")
    private Double fantasy_points;
    private Integer rank;

    /**
     * No args constructor for use in serialization
     *
     */
    public Stat() {
    }

    /**
     *
     * @param game_id
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
    public Stat(String game_id, Integer team_id, String team_abbreviation, String url_date, String url_teams, Double pts, Double reb, Double ast, Double blk, Double stl, Double tov, Double fg_pct, Double fg3m, Double fg3_pct, Double ftm, Double ft_pct, Double nba_fantasy_pts, Double fantasy_points) {
        this.game_id = game_id;
        this.team_id = team_id;
        this.team_abbreviation = team_abbreviation;
        this.url_date = url_date;
        this.url_teams = url_teams;
        this.pts = pts;
        this.reb = reb;
        this.ast = ast;
        this.blk = blk;
        this.stl = stl;
        this.tov = tov;
        this.fg_pct = fg_pct;
        this.fg3m = fg3m;
        this.fg3_pct = fg3_pct;
        this.ftm = ftm;
        this.ft_pct = ft_pct;
        this.nba_fantasy_pts = nba_fantasy_pts;
        this.fantasy_points = fantasy_points;
    }

    @JsonProperty("GAME_ID")
    public String getGame_id() {
        return game_id;
    }

    @JsonProperty("GAME_ID")
    public void setGame_id(String game_id) {
        this.game_id = game_id;
    }

    @JsonProperty("TEAM_ID")
    public Integer getTeam_id() {
        return team_id;
    }

    @JsonProperty("TEAM_ID")
    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    @JsonProperty("TEAM_ABBREVIATION")
    public String getTeam_abbreviation() {
        return team_abbreviation;
    }

    @JsonProperty("TEAM_ABBREVIATION")
    public void setTeam_abbreviation(String team_abbreviation) {
        this.team_abbreviation = team_abbreviation;
    }

    @JsonProperty("URL_DATE")
    public String getUrl_date() {
        return url_date;
    }

    @JsonProperty("URL_DATE")
    public void setUrl_date(String url_date) {
        this.url_date = url_date;
    }

    @JsonProperty("URL_TEAMS")
    public String getUrl_teams() {
        return url_teams;
    }

    @JsonProperty("URL_TEAMS")
    public void setUrl_teams(String url_teams) {
        this.url_teams = url_teams;
    }

    @JsonProperty("PTS")
    public Double getPts() {
        return pts;
    }

    @JsonProperty("PTS")
    public void setPts(Double pts) {
        this.pts = pts;
    }

    @JsonProperty("REB")
    public Double getReb() {
        return reb;
    }

    @JsonProperty("REB")
    public void setReb(Double reb) {
        this.reb = reb;
    }

    @JsonProperty("AST")
    public Double getAst() {
        return ast;
    }

    @JsonProperty("AST")
    public void setAst(Double ast) {
        this.ast = ast;
    }

    @JsonProperty("BLK")
    public Double getBLK() {
        return blk;
    }

    @JsonProperty("BLK")
    public void setBlk(Double blk) {
        this.blk = blk;
    }

    @JsonProperty("STL")
    public Double getStl() {
        return stl;
    }

    @JsonProperty("STL")
    public void setStl(Double stl) {
        this.stl = stl;
    }

    @JsonProperty("TOV")
    public Double getTov() {
        return tov;
    }

    @JsonProperty("TOV")
    public void setTov(Double tov) {
        this.tov = tov;
    }

    @JsonProperty("FG_PCT")
    public Double getFg_pct() {
        return fg_pct==null?null:fg_pct*100;
    }

    @JsonProperty("FG_PCT")
    public void setFg_pct(Double fg_pct) {
        this.fg_pct = fg_pct;
    }

    @JsonProperty("FG3M")
    public Double getFg3m() {
        return fg3m;
    }

    @JsonProperty("FG3M")
    public void setFg3m(Double fg3m) {
        this.fg3m = fg3m;
    }

    @JsonProperty("FG3_PCT")
    public Double getFg3_pct() {
        return fg3_pct==null?null:fg3_pct*100;
    }

    @JsonProperty("FG3_PCT")
    public void setFg3_pct(Double fg3_pct) {
        this.fg3_pct = fg3_pct;
    }

    @JsonProperty("FTM")
    public Double getFtm() {
        return ftm;
    }

    @JsonProperty("FTM")
    public void setFtm(Double ftm) {
        this.ftm = ftm;
    }

    @JsonProperty("FT_PCT")
    public Double getFt_pct() {
        return ft_pct==null?null:ft_pct*100;
    }

    @JsonProperty("FT_PCT")
    public void setFt_pct(Double ft_pct) {
        this.ft_pct = ft_pct;
    }

    @JsonProperty("NBA_FANTASY_PTS")
    public Double getNba_fantasy_pts() {
        return nba_fantasy_pts;
    }

    @JsonProperty("NBA_FANTASY_PTS")
    public void setNba_fantasy_pts(Double nba_fantasy_pts) {
        this.nba_fantasy_pts = nba_fantasy_pts;
    }

    @JsonProperty("FANTASY_POINTS")
    public Double getFantasy_points() {
        return fantasy_points;
    }

    @JsonProperty("FANTASY_POINTS")
    public void setFantasy_points(Double fantasy_points) {
        this.fantasy_points = fantasy_points;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Double getValue() {
        if(pts != null) {
            return Double.valueOf(pts);
        }
        if(reb != null) {
            return Double.valueOf(reb);
        }
        if(ast != null) {
            return Double.valueOf(ast);
        }
        if(blk != null) {
            return Double.valueOf(blk);
        }
        if(stl != null) {
            return Double.valueOf(stl);
        }
        if(tov != null) {
            return Double.valueOf(tov);
        }
        if(fg_pct != null) {
            return fg_pct;
        }
        if(fg3m != null) {
            return Double.valueOf(fg3m);
        }
        if(fg3_pct != null) {
            return fg3_pct;
        }
        if(ftm != null) {
            return Double.valueOf(ftm);
        }
        if(ft_pct != null) {
            return ft_pct;
        }
        if(nba_fantasy_pts != null) {
            return nba_fantasy_pts;
        }
        if(fantasy_points != null) {
            return fantasy_points;
        }
        return null;
    }
}
