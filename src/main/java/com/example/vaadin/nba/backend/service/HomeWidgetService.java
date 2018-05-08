package com.example.vaadin.nba.backend.service;

import com.example.vaadin.nba.backend.data.entity.widget.Category;
import com.example.vaadin.nba.backend.data.entity.widget.Item;
import com.example.vaadin.nba.backend.data.entity.widget.Stat;
import com.example.vaadin.nba.backend.data.entity.widget.Widget;
import com.example.vaadin.nba.backend.data.enumeration.PlayerOrTeam;
import com.example.vaadin.nba.backend.data.enumeration.WidgetsEndpoint;
import com.example.vaadin.nba.backend.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeWidgetService {

    private final WidgetsService widgetsService;
    private Widget widget;

    @Autowired
    public HomeWidgetService(WidgetsService widgetsService) {
        this.widgetsService = widgetsService;
    }

    @Cacheable(cacheNames=Constants.Cache.HOME_WIDGETS, key="#endpoint")
    public Map<PlayerOrTeam, List<Category>> getHomeWidgets(final WidgetsEndpoint endpoint) {
        widget = widgetsService.getWidgets(endpoint);
        List<Item> listItems = widget.getCategories();
        Map<PlayerOrTeam, List<Category>> mapListCategories = new HashMap();
        List<Category> listCategories = null;
        for (Item item : listItems) {
            switch(item.getUid()) {
                case Constants.Widgets.Uid.DAILY_PLAYERS:
                case Constants.Widgets.Uid.PLAYOFFS_PLAYERS:
                case Constants.Widgets.Uid.SEASON_PLAYERS:
                    mapListCategories.put(PlayerOrTeam.Player, getCategoryList(item, PlayerOrTeam.Player));
                    break;
                case Constants.Widgets.Uid.DAILY_TEAMS:
                case Constants.Widgets.Uid.PLAYOFFS_TEAMS:
                case Constants.Widgets.Uid.SEASON_TEAMS:
                    mapListCategories.put(PlayerOrTeam.Team, getCategoryList(item, PlayerOrTeam.Team));
                    break;
            }
            /*if(item.getUid().equalsIgnoreCase(String.format("home_daily_%ss", playerOrTeam))
                || item.getUid().equalsIgnoreCase(String.format("home_daily_%ss_playoffs", playerOrTeam))
                || item.getUid().equalsIgnoreCase(String.format("season_%ss", playerOrTeam))) {
                listCategories = getCategoryList(item, playerOrTeam);
                break;
            }*/
        }
        return mapListCategories;
    }

    private static List<Category> getCategoryList(Item item, PlayerOrTeam playerOrTeam) {
        List<Category> listCategories = item.getItems();
        for (Category category: listCategories) {
            List<? extends Stat> listStat = null;
            switch (playerOrTeam) {
                case Player:
                    listStat = category.getPlayerstats();
                    break;
                case Team:
                    listStat = category.getTeamstats();
                    break;
            }
            listStat.get(0).setRank(1);
            for(int i = 1; i < listStat.size(); i++) {
                if(listStat.get(i).getValue().equals(listStat.get(i-1).getValue())) {
                    listStat.get(i).setRank(listStat.get(i-1).getRank());
                } else {
                    listStat.get(i).setRank(i+1);
                }
            }
        }
        return listCategories;
    }
}
