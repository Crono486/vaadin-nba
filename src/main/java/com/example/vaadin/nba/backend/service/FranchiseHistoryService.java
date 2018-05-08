package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.Franchise;
import com.example.vaadin.nba.backend.data.entity.stats.ResultSet;
import com.example.vaadin.nba.backend.data.enumeration.StatsEndpoint;
import com.example.vaadin.nba.backend.data.enumeration.LeagueID;
import com.example.vaadin.nba.backend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FranchiseHistoryService {

    private final StatsService statsService;
    private final String IS_MAIN_DATASET = "IsMainDataSet";

    @Autowired
    public FranchiseHistoryService(StatsService statsService) {
        this.statsService = statsService;
    }

    @Cacheable(Constants.Cache.FRANCHISE_HISTORY)
    public List<Franchise> getFranchiseHistory() {
        return getFranchiseHistory(LeagueID.NBA);
    }

    @Cacheable(Constants.Cache.FRANCHISE_HISTORY)
    public List<Franchise> getFranchiseHistory(LeagueID leagueID) {

        List<Franchise> list = new ArrayList<>();
        List<ResultSet> rsList = statsService.getStats(StatsEndpoint.franchisehistory,
                "LeagueID=" + leagueID);
        List<String> teamIdList = new ArrayList<>();
        for (ResultSet rs: rsList) {
            if(rs.getName().equals("FranchiseHistory")) {
                List<Map<String, String>> franchiseHistory = new ArrayList<>();
                List<String> headerList = rs.getHeaders();
                List<List<String>> valueListList = rs.getRowSet();
                for (List<String> valueList : valueListList) {
                    Map<String, String> dataSet = new HashMap<>();
                    for(int i = 0; i < valueList.size(); i++) {

                        dataSet.put(headerList.get(i), valueList.get(i));
                    }
                    if(!teamIdList.contains(dataSet.get("TEAM_ID"))) {
                        teamIdList.add(dataSet.get("TEAM_ID"));
                        dataSet.put(IS_MAIN_DATASET, Constants.TRUE);//TODO
                    } else {
                        dataSet.put(IS_MAIN_DATASET, Constants.FALSE);//TODO
                    }
                    franchiseHistory.add(dataSet);
                }
                for (Map<String, String> row : franchiseHistory) {
                    Franchise franchise = new Franchise();
                    franchise.values = row;
                    list.add(franchise);
                }
            }
        }
        return list;
    }
}
