package com.example.vaadin.nba.backend.data;

import java.util.HashMap;
import java.util.Map;

public class CommonTeamRoster {

    public Map<String, String> values;

    public CommonTeamRoster() {
    }

    // dirty to use a season as TreeRowParent
    public CommonTeamRoster(String season) {
        values = new HashMap<>();
        values.put("PLAYER", season);
    }

    public String getTeamId() {
        return values.get("TeamID");
    }

    public String getSeason() {
        return values.get("SEASON");
    }

    public String getLeagueId() {
        return values.get("LeagueID");
    }

    public String getPlayer() {
        return values.get("PLAYER");
    }

    public String getNum() {
        return values.get("NUM");
    }

    public String getPosition() {
        if(null == values.get("POSITION")) {
            return "";
        } else {
            return values.get("POSITION")
                    .replace("G", "Guard")
                    .replace("F", "Forward")
                    .replace("C", "Center");
        }
    }

    public String getHeight() {
        return values.get("HEIGHT");
    }

    public String getWeight() {
        if(null == values.get("WEIGHT")) {
            return "";
        } else {
            return values.get("WEIGHT") + " lbs";
        }
    }

    public String getBirthDate() {
        return values.get("BIRTH_DATE");
    }

    public String getAge() {
        if(null == values.get("AGE")) {
            return "";
        } else {
            return values.get("AGE").substring(0, 2);
        }
    }

    public String getExp() {
        if(null == values.get("EXP")) {
            return "";
        } else {
            return values.get("EXP").replace("R", "Rookie");
        }
    }

    public String getSchool() {
        return values.get("SCHOOL");
    }

    public Integer getPlayerId() {
        return Integer.valueOf(values.get("PLAYER_ID"));
    }
}
