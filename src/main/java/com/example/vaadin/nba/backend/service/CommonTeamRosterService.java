package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.CommonTeamRoster;
import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;
import com.example.vaadin.nba.backend.data.enumeration.StatsEndpoint;
import com.example.vaadin.nba.backend.data.enumeration.LeagueID;
import com.example.vaadin.nba.backend.util.Constants;
import com.example.vaadin.nba.backend.util.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CommonTeamRosterService {

    private final StatsService statsService;

    @Autowired
    public CommonTeamRosterService(StatsService statsService) {
        this.statsService = statsService;
    }

    @Cacheable(Constants.Cache.COMMON_TEAM_ROSTER)
    public List<CommonTeamRoster> getCommonTeamRoster(String teamID, String season) {
        return getCommonTeamRoster(LeagueID.NBA, teamID, season);
    }

    @Cacheable(Constants.Cache.COMMON_TEAM_ROSTER)
    public List<CommonTeamRoster> getCommonTeamRoster(LeagueID leagueID, String teamID, String season) {

        List<CommonTeamRoster> list = new ArrayList<>();
        List<ResultSet> rsList = statsService.getStats(StatsEndpoint.commonteamroster,
                "LeagueID=" + leagueID,
                "TeamID=" + teamID,
                "Season=" + season);
        for (ResultSet rs: rsList) {
            if(rs.getName().equals("CommonTeamRoster")) {
                List<Map<String, String>> commonTeamRosterList = ResultSetUtil.getValueMapList(rs);
                for (Map<String, String> row : commonTeamRosterList) {
                    CommonTeamRoster commonTeamRoster = new CommonTeamRoster();
                    commonTeamRoster.values = row;
                    list.add(commonTeamRoster);
                }
            }
        }
        return list;
    }
}
