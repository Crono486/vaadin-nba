package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.DraftHistory;
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
public class DraftHistoryService {

    private final StatsService statsService;
    private final String IS_MAIN_DATASET = "IsMainDataSet";

    @Autowired
    public DraftHistoryService(StatsService statsService) {
        this.statsService = statsService;
    }

    @Cacheable(Constants.Cache.DRAFT_HISTORY)
    public List<DraftHistory> getDraftHistory(Integer season) {
        return getDraftHistory(LeagueID.NBA, season);
    }

    @Cacheable(Constants.Cache.DRAFT_HISTORY)
    public List<DraftHistory> getDraftHistory(LeagueID leagueID, Integer season) {

        List<DraftHistory> list = new ArrayList<>();
        List<ResultSet> rsList = statsService.getStats(StatsEndpoint.drafthistory,
                "LeagueID=" + leagueID,
                "Season=" + season);
        for (ResultSet rs: rsList) {
            if(rs.getName().equals("DraftHistory")) {
                List<Map<String, String>> draftHistoryList = ResultSetUtil.getValueMapList(rs);
                for (Map<String, String> row : draftHistoryList) {
                    DraftHistory draftHistory = new DraftHistory();
                    draftHistory.values = row;
                    list.add(draftHistory);
                }
            }
        }
        return list;
    }
}
