package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.CommonPlayerInfo;
import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;
import com.example.vaadin.nba.backend.data.enumeration.LeagueID;
import com.example.vaadin.nba.backend.data.enumeration.StatsEndpoint;
import com.example.vaadin.nba.backend.util.Constants;
import com.example.vaadin.nba.backend.util.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonPlayerInfoService {

    private final StatsService statsService;

    @Autowired
    public CommonPlayerInfoService(StatsService statsService) {
        this.statsService = statsService;
    }

    @Cacheable(Constants.Cache.COMMON_PLAYER_INFO)
    public CommonPlayerInfo getCommonPlayerInfo(String playerID) {
        return getCommonPlayerInfo(LeagueID.NBA, playerID);
    }

    @Cacheable(Constants.Cache.COMMON_PLAYER_INFO)
    public CommonPlayerInfo getCommonPlayerInfo(LeagueID leagueID, String playerID) {

        List<ResultSet> rsList = statsService.getStats(StatsEndpoint.commonplayerinfo,
                "LeagueID=" + leagueID,
                "PlayerID=" + playerID);
        CommonPlayerInfo commonPlayerInfo = new CommonPlayerInfo();
        for (ResultSet rs: rsList) {
            if(rs.getName().equals("CommonPlayerInfo") || rs.getName().equals("PlayerHeadlineStats")) {
                List<Map<String, String>> commonPlayerInfoList = ResultSetUtil.getValueMapList(rs);
                for (Map<String, String> row : commonPlayerInfoList) {
                    commonPlayerInfo.values.putAll(row);
                }
            }
        }
        return commonPlayerInfo;
    }
}
