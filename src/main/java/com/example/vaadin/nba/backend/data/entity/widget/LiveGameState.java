package com.example.vaadin.nba.backend.data.entity.widget;

import com.example.vaadin.nba.backend.data.entity.JsonEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "LEAGUE_ID",
        "LIVE_GAME_COUNT",
        "LIVE_GAME_FLAG"
})
public class LiveGameState extends JsonEntity {

    @JsonProperty("LEAGUE_ID")
    private String league_id;
    @JsonProperty("LIVE_GAME_COUNT")
    private Integer live_game_count;
    @JsonProperty("LIVE_GAME_FLAG")
    private String live_game_flag;

    /**
     * No args constructor for use in serialization
     *
     */
    public LiveGameState() {
    }

    /**
     *
     * @param live_game_count
     * @param league_id
     * @param live_game_flag
     */
    public LiveGameState(String league_id, Integer live_game_count, String live_game_flag) {
        super();
        this.league_id = league_id;
        this.live_game_count = live_game_count;
        this.live_game_flag = live_game_flag;
    }

    @JsonProperty("LEAGUE_ID")
    public String getLeague_id() {
        return league_id;
    }

    @JsonProperty("LEAGUE_ID")
    public void setLeague_id(String league_id) {
        this.league_id = league_id;
    }

    @JsonProperty("LIVE_GAME_COUNT")
    public Integer getLive_game_count() {
        return live_game_count;
    }

    @JsonProperty("LIVE_GAME_COUNT")
    public void setLive_game_count(Integer live_game_count) {
        this.live_game_count = live_game_count;
    }

    @JsonProperty("LIVE_GAME_FLAG")
    public String getLive_game_flag() {
        return live_game_flag;
    }

    @JsonProperty("LIVE_GAME_FLAG")
    public void setLive_game_flag(String live_game_flag) {
        this.live_game_flag = live_game_flag;
    }
}