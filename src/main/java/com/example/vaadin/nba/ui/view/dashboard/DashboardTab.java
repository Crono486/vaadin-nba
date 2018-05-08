package com.example.vaadin.nba.ui.view.dashboard;

import com.example.vaadin.nba.backend.data.entity.widget.Category;
import com.example.vaadin.nba.backend.data.entity.widget.PlayerStat;
import com.example.vaadin.nba.backend.data.entity.widget.Stat;
import com.example.vaadin.nba.backend.data.entity.widget.TeamStat;
import com.example.vaadin.nba.backend.data.enumeration.PlayerOrTeam;
import com.example.vaadin.nba.backend.data.enumeration.WidgetsEndpoint;
import com.example.vaadin.nba.backend.service.HomeWidgetService;
import com.example.vaadin.nba.backend.util.Constants;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.example.vaadin.nba.ui.view.player.CommonPlayerInfoView;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public abstract class DashboardTab extends VerticalLayout {

    private final NavigationManager navigationManager;
    private final HomeWidgetService homeWidgetService;
    protected WidgetsEndpoint endpoint;

    protected RadioButtonGroup<PlayerOrTeam> radioPlayerOrTeam;
    protected CssLayout teaserGroupPlayers;
    protected CssLayout teaserGroupTeams;
    protected List<Category> playersCategoryList;
    protected List<Category> teamsCategoryList;

    protected float gridWidth = 430;
    protected float rankWidth = 37;
    protected float buttonWidth = 37;
    protected double gridHeightByRows = 5;

    public DashboardTab(NavigationManager navigationManager, HomeWidgetService homeWidgetService) {
        this.navigationManager = navigationManager;
        this.homeWidgetService = homeWidgetService;
    }

    @PostConstruct
    public void init() {
        setSizeFull();
        addStyleName(ValoTheme.PANEL_BORDERLESS);

        radioPlayerOrTeam = new RadioButtonGroup<>();
        radioPlayerOrTeam.setItems(Arrays.asList(PlayerOrTeam.values()));
        radioPlayerOrTeam.setSelectedItem(PlayerOrTeam.Player);
        radioPlayerOrTeam.addStyleName(ValoTheme.OPTIONGROUP_HORIZONTAL);
        radioPlayerOrTeam.addValueChangeListener(event -> getStats(endpoint, event.getValue()));
    }

    protected void enter() {
        getStats(endpoint, radioPlayerOrTeam.getSelectedItem().get());
    }

    protected void getStats(WidgetsEndpoint endpoint, PlayerOrTeam playerOrTeam) {

        int index = 0;
        switch (playerOrTeam) {
            case Player:
                playersCategoryList = homeWidgetService.getHomeWidgets(endpoint).get(playerOrTeam);
                if (teaserGroupPlayers == null) {
                    setPlayerstatGrid();
                } else {
                    for (Category category : playersCategoryList) {
                        ((Grid<PlayerStat>) ((VerticalLayout) teaserGroupPlayers.getComponent(index)).getComponent(1)).setItems(category.getPlayerstats());
                        index++;
                    }
                }
                if (teaserGroupTeams != null) {
                    teaserGroupTeams.setVisible(false);
                }
                teaserGroupPlayers.setVisible(true);
                break;
            case Team:
                teamsCategoryList = homeWidgetService.getHomeWidgets(endpoint).get(playerOrTeam);
                if (teaserGroupTeams == null) {
                    setTeamstatGrid();
                } else {
                    for (Category category : teamsCategoryList) {
                        ((Grid<TeamStat>) ((VerticalLayout) teaserGroupTeams.getComponent(index)).getComponent(1)).setItems(category.getTeamstats());
                        index++;
                    }
                }
                if (teaserGroupPlayers != null) {
                    teaserGroupPlayers.setVisible(false);
                }
                teaserGroupTeams.setVisible(true);
                break;
        }
    }

    protected void setPlayerstatGrid() {
        teaserGroupPlayers = new CssLayout();
        teaserGroupPlayers.addStyleName("dashboard");
        for (Category category : playersCategoryList) {
            VerticalLayout teaser = new VerticalLayout();
            Grid<PlayerStat> grid = new Grid<>();
            teaser.setSizeUndefined();
            teaser.setMargin(true);
            grid.setHeaderVisible(false);
            grid.setHeightByRows(gridHeightByRows);
            grid.setWidth(gridWidth, Unit.PIXELS);

            grid.addColumn(PlayerStat::getRank).setWidth(rankWidth).setStyleGenerator(item -> "v-align-right");
            grid.addColumn(PlayerStat::getPlayer_name);
            grid.addColumn(PlayerStat::getTeam_abbreviation).setWidth(64);
            addStatColumn(grid, category);
            grid.addColumn(player -> "i", new ButtonRenderer<>(event -> {
                PlayerStat playerStat = event.getItem();
                navigationManager.navigateTo(CommonPlayerInfoView.class, playerStat.getPlayer_id());
            })).setWidth(buttonWidth);

            Label labelTeaserHeadline = new Label(category.getTitle());
            labelTeaserHeadline.addStyleName(ValoTheme.LABEL_H3);
            grid.setItems(category.getPlayerstats());

            teaser.addComponents(labelTeaserHeadline, grid);
            teaserGroupPlayers.addComponent(teaser);
            addComponent(teaserGroupPlayers);
            setExpandRatio(teaserGroupPlayers, 1);
        }
    }

    protected void setTeamstatGrid() {
        teaserGroupTeams = new CssLayout();
        teaserGroupTeams.addStyleName("dashboard");
        for(Category category: teamsCategoryList) {
            VerticalLayout teaser = new VerticalLayout();
            Grid<TeamStat> grid = new Grid<>();
            teaser.setSizeUndefined();
            teaser.setMargin(true);
            grid.setHeaderVisible(false);
            grid.setHeightByRows(gridHeightByRows);
            grid.setWidth(gridWidth, Unit.PIXELS);

            grid.addColumn(TeamStat::getRank).setWidth(rankWidth).setStyleGenerator(item -> "v-align-right");
            grid.addColumn(TeamStat::getTeam);
            addStatColumn(grid, category);

            Label labelTeaserHeadline = new Label(category.getTitle());
            labelTeaserHeadline.addStyleName(ValoTheme.LABEL_H3);
            grid.setItems(category.getTeamstats());

            teaser.addComponents(labelTeaserHeadline, grid);
            teaserGroupTeams.addComponent(teaser);
            addComponent(teaserGroupTeams);
            setExpandRatio(teaserGroupTeams, 1);
        }
    }

    private void addStatColumn(Grid<? extends Stat> grid, Category category) {
        Grid.Column<? extends Stat, ?> column = null;
        switch (category.getName()) {
            case Constants.Stats.Key.PTS:
                column = grid.addColumn(i -> i.getPts());
                break;
            case Constants.Stats.Key.REB:
                column = grid.addColumn(i -> i.getReb());
                break;
            case Constants.Stats.Key.AST:
                column = grid.addColumn(i -> i.getAst());
                break;
            case Constants.Stats.Key.BLK:
                column = grid.addColumn(i -> i.getBLK());
                break;
            case Constants.Stats.Key.STL:
                column = grid.addColumn(i -> i.getStl());
                break;
            case Constants.Stats.Key.TOV:
                column = grid.addColumn(i -> i.getTov());
                break;
            case Constants.Stats.Key.FG_PCT:
                column = grid.addColumn(i -> String.format(Locale.US, "%.1f", i.getFg_pct()));
                break;
            case Constants.Stats.Key.FG3M:
                column = grid.addColumn(i -> i.getFg3m());
                break;
            case Constants.Stats.Key.FG3_PCT:
                column = grid.addColumn(i -> String.format(Locale.US, "%.1f", i.getFg3_pct()));
                break;
            case Constants.Stats.Key.FTM:
                column = grid.addColumn(i -> i.getFtm());
                break;
            case Constants.Stats.Key.FT_PCT:
                column = grid.addColumn(i -> String.format(Locale.US, "%.1f", i.getFt_pct()));
                break;
            case Constants.Stats.Key.NBA_FANTASY_PTS:
                column = grid.addColumn(i -> i.getNba_fantasy_pts());
                break;
            case Constants.Stats.Key.FANTASY_POINTS:
                column = grid.addColumn(i -> i.getFantasy_points());
                break;
        }
        column.setWidth(88).setStyleGenerator(item -> "v-align-right");
    }
}
