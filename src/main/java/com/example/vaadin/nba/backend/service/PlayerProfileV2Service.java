package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.PlayerProfileV2;
import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;
import com.example.vaadin.nba.backend.data.enumeration.LeagueID;
import com.example.vaadin.nba.backend.data.enumeration.PerMode;
import com.example.vaadin.nba.backend.data.enumeration.StatsEndpoint;
import com.example.vaadin.nba.backend.util.Constants;
import com.example.vaadin.nba.backend.util.ResultSetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PlayerProfileV2Service {

    private final StatsService statsService;

    @Autowired
    public PlayerProfileV2Service(StatsService statsService) {
        this.statsService = statsService;
    }

    @Cacheable(cacheNames=Constants.Cache.PLAYER_PROFILE_V2)
    public List<PlayerProfileV2> getPlayerProfileV2(String playerId) {

        List<PlayerProfileV2> list = new ArrayList<>();
        List<ResultSet> rsList = statsService.getStats(StatsEndpoint.playerprofilev2,
                "LeagueID=" + LeagueID.NBA,
                "PlayerID=" + playerId,
                "PerMode=" + PerMode.PerGame);
        for (ResultSet rs: rsList) {
            List<Map<String, String>> playerProfileV2List = ResultSetUtil.getValueMapList(rs);
            for (Map<String, String> row : playerProfileV2List) {
                PlayerProfileV2 playerProfileV2 = new PlayerProfileV2();
                playerProfileV2.values = row;
                list.add(playerProfileV2);
            }
        }
        return list;
    }
}
