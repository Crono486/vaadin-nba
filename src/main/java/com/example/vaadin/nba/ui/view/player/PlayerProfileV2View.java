package com.example.vaadin.nba.ui.view.player;

import com.example.vaadin.nba.backend.data.PlayerProfileV2;
import com.example.vaadin.nba.backend.service.PlayerProfileV2Service;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView
public class PlayerProfileV2View extends VerticalLayout implements View {

    private final NavigationManager navigationManager;
    private final PlayerProfileV2Service playerProfileV2Service;

    private Label headline;
    private TreeGrid<PlayerProfileV2> grid;
    private List<PlayerProfileV2> commonPlayerInfo;
    private VerticalLayout legendGroup;
    private String playerId;

    public PlayerProfileV2View(NavigationManager navigationManager, PlayerProfileV2Service playerProfileV2Service) {
        this.navigationManager = navigationManager;
        this.playerProfileV2Service = playerProfileV2Service;
    }

    @PostConstruct
    public void init() {
        setSizeFull();
        setMargin(false);

        headline = new Label();
        grid = new TreeGrid<>();
        legendGroup = new VerticalLayout();

        headline.addStyleName(ValoTheme.LABEL_H1);

        grid.addStyleName(ValoTheme.TREETABLE_SMALL);
        grid.setWidth("100%");
        grid.setHeight("100%");
        grid.addColumn(PlayerProfileV2::getGamesPlayed).setCaption("Games Played");
        grid.addColumn(PlayerProfileV2::getGamesStarted).setCaption("Games Started");

        addComponents(headline, grid, legendGroup);
        setExpandRatio(grid, 1);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameter = event.getParameters();
        String parameters[] = parameter.split("&");
        playerId = parameters[0];
        headline.setValue(parameters[1]);
        grid.setItems(playerProfileV2Service.getPlayerProfileV2(playerId));
        /*TreeDataProvider<PlayerProfileV2> dataProvider = (TreeDataProvider<PlayerProfileV2>) grid.getDataProvider();
        TreeData<PlayerProfileV2> data = dataProvider.getTreeData();
        PlayerProfileV2 playerProfileV2 = new PlayerProfileV2();
        data.addItem(null, playerProfileV2);
        // after adding / removing data, data provider needs to be refreshed
        dataProvider.refreshAll();*/
    }
}
