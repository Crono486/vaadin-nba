package com.example.vaadin.nba.ui.view.dashboard;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

@SpringView
public class DashboardView extends VerticalLayout implements View {

    private final DailyStats dailyStats;
    private final PlayoffsStats playoffsStats;
    private final SeasonStats seasonStats;
    private TabSheet tabDashboardStats;
    private Label labelHeadline;

    public DashboardView(DailyStats dailyStats, PlayoffsStats playoffsStats, SeasonStats seasonStats) {
        this.dailyStats = dailyStats;
        this.playoffsStats = playoffsStats;
        this.seasonStats = seasonStats;
    }

    @PostConstruct
    public void init() {
        setHeightUndefined();
        setMargin(false);

        labelHeadline = new Label("Dashboard");
        labelHeadline.addStyleName(ValoTheme.LABEL_H1);

        tabDashboardStats = new TabSheet();
        tabDashboardStats.addTab(dailyStats, "Daily Leaders");
        tabDashboardStats.addTab(playoffsStats, "Playoffs Leaders");
        tabDashboardStats.addTab(seasonStats, "Season Leaders");
        tabDashboardStats.addSelectedTabChangeListener(event -> ((DashboardTab)event.getTabSheet().getSelectedTab()).enter());
        ((DashboardTab)tabDashboardStats.getSelectedTab()).enter();

        addComponents(labelHeadline, tabDashboardStats);
    }
}
