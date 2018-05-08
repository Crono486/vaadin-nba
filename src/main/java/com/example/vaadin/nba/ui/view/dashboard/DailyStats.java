package com.example.vaadin.nba.ui.view.dashboard;

import com.example.vaadin.nba.backend.data.enumeration.WidgetsEndpoint;
import com.example.vaadin.nba.backend.service.HomeWidgetService;
import com.example.vaadin.nba.backend.util.CalendarUtil;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringComponent
@UIScope
public class DailyStats extends DashboardTab {

    private Label labelDate;

    public DailyStats(NavigationManager navigationManager, HomeWidgetService homeWidgetService) {
        super(navigationManager, homeWidgetService);
    }

    @PostConstruct
    public void init() {
        super.init();
        endpoint = WidgetsEndpoint.home_daily;

        // -1 for yesterday (today-1)
        labelDate = new Label(CalendarUtil.getDateString(-1));

        setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        addComponents(labelDate, radioPlayerOrTeam);

        labelDate.addStyleName(ValoTheme.LABEL_TINY);
    }
}
