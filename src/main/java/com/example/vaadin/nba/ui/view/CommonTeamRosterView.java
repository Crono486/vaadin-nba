package com.example.vaadin.nba.ui.view;

import com.example.vaadin.nba.backend.data.CommonTeamRoster;
import com.example.vaadin.nba.backend.service.CommonTeamRosterService;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.example.vaadin.nba.ui.view.player.PlayerProfileV2View;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView
public class CommonTeamRosterView extends VerticalLayout implements View {

    private final NavigationManager navigationManager;
    private final CommonTeamRosterService commonTeamRosterService;

    private Label headline;
    private TreeGrid<CommonTeamRoster> grid;
    private List<CommonTeamRoster> commonTeamRoster;
    private VerticalLayout legendGroup;
    private String teamId;

    public CommonTeamRosterView(NavigationManager navigationManager, CommonTeamRosterService commonTeamRosterService) {
        this.navigationManager = navigationManager;
        this.commonTeamRosterService = commonTeamRosterService;
    }

    @PostConstruct
    public void init() {
        setHeightUndefined();
        setMargin(false);

        headline = new Label();
        grid = new TreeGrid<>();
        legendGroup = new VerticalLayout();

        headline.addStyleName(ValoTheme.LABEL_H1);

        grid.addStyleName(ValoTheme.TREETABLE_SMALL);
        grid.setWidth("100%");
        grid.setHeight("100%");
        grid.addItemClickListener((ItemClickListener<CommonTeamRoster>) event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                CommonTeamRoster ctr = event.getItem();
                if(((TreeDataProvider<CommonTeamRoster>)event.getSource().getDataProvider()).getTreeData().getParent(ctr) == null) {
                    if(grid.isExpanded(ctr)) {
                        grid.collapse(ctr);
                    } else {
                        grid.expand(ctr);
                    }
                } else {
                    CommonTeamRoster commonTeamRoster = event.getItem();
                    LoggerFactory.getLogger(getClass()).info("playerId: " + event.getItem().getPlayerId());
                    navigationManager.navigateTo(PlayerProfileV2View.class, commonTeamRoster.getPlayerId() + "&" +
                            commonTeamRoster.getPlayer());
                }
            }
        });
        grid.addExpandListener(event -> expand(event.getExpandedItem()));
        grid.addColumn(CommonTeamRoster::getPlayer).setCaption("Player");
        grid.addColumn(CommonTeamRoster::getNum).setCaption("#").setWidth(80);
        grid.addColumn(CommonTeamRoster::getPosition).setCaption("Position");
        grid.addColumn(CommonTeamRoster::getHeight).setCaption("Height");
        grid.addColumn(CommonTeamRoster::getWeight).setCaption("Weight");
        grid.addColumn(CommonTeamRoster::getBirthDate).setCaption("Birthdate");
        grid.addColumn(CommonTeamRoster::getAge).setCaption("Age").setWidth(80);
        grid.addColumn(CommonTeamRoster::getExp).setCaption("Exp").setComparator((a, b) -> compareExp(a.getExp(), b.getExp()));
        grid.addColumn(CommonTeamRoster::getSchool).setCaption("School");

        addComponents(headline, grid, legendGroup);
        setExpandRatio(grid, 1);
    }

    private int compareExp(String a, String b) {
        if(!a.isEmpty() && !b.isEmpty()) {
            if (a.equals("Rookie") && b.equals("Rookie")) {
                return 0;
            } else if (a.equals("Rookie") && !b.equals("Rookie")) {
                return -1;
            } else if (!a.equals("Rookie") && b.equals("Rookie")) {
                return 1;
            } else {
                return Integer.valueOf(a).compareTo(Integer.valueOf(b));
            }
        } else {
            return a.compareTo(b);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String parameter = event.getParameters();
        String parameters[] = parameter.split("&");
        teamId = parameters[0];
        Integer startYear = Integer.valueOf(parameters[1]);
        Integer endYear = Integer.valueOf(parameters[2]);
        headline.setValue(parameters[3]);
        TreeDataProvider<CommonTeamRoster> dataProvider = (TreeDataProvider<CommonTeamRoster>) grid.getDataProvider();
        for(int i = startYear; i <= endYear; i++) {
            String season = i + "-" + String.format("%02d", (Integer.valueOf(i)+1)%100);
            TreeData<CommonTeamRoster> data = dataProvider.getTreeData();
            CommonTeamRoster seasonRoster = new CommonTeamRoster(season);
            data.addItem(null, seasonRoster);
            data.addItem(seasonRoster, new CommonTeamRoster(""));
        }
        // after adding / removing data, data provider needs to be refreshed
        dataProvider.refreshAll();
    }

    private void expand(CommonTeamRoster season) {

        commonTeamRoster = commonTeamRosterService.getCommonTeamRoster(teamId, season.getPlayer());

        TreeDataProvider<CommonTeamRoster> dataProvider = (TreeDataProvider<CommonTeamRoster>) grid.getDataProvider();
        TreeData<CommonTeamRoster> data = dataProvider.getTreeData();

        List<CommonTeamRoster> dummy = data.getChildren(season);
        if(dummy.size() == 1) {
            data.removeItem(dummy.get(0));
            data.addItems(season, commonTeamRoster);

            // after adding / removing data, data provider needs to be refreshed
            dataProvider.refreshAll();
        }
    }
}
