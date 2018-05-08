package com.example.vaadin.nba.ui.view.dashboard;

import com.example.vaadin.nba.backend.data.enumeration.WidgetsEndpoint;
import com.example.vaadin.nba.backend.service.HomeWidgetService;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class PlayoffsStats extends DashboardTab {

    public PlayoffsStats(NavigationManager navigationManager, HomeWidgetService homeWidgetService) {
        super(navigationManager, homeWidgetService);
    }

    @PostConstruct
    public void init() {
        super.init();
        endpoint = WidgetsEndpoint.home_playoffs;

        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        addComponent(radioPlayerOrTeam);
    }
}
