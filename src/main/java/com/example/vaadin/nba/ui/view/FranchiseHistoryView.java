package com.example.vaadin.nba.ui.view;

import com.example.vaadin.nba.backend.data.Franchise;
import com.example.vaadin.nba.backend.service.FranchiseHistoryService;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.ItemClickListener;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringView
public class FranchiseHistoryView extends VerticalLayout implements View {
    private final NavigationManager navigationManager;
    private final FranchiseHistoryService franchiseHistoryService;

    private Panel searchPanel;
    private TextField searchField;
    private Button searchButton;
    private Label legendLabel;
    private Label headline;
    private HorizontalLayout buttonGroup;
    private TreeGrid<Franchise> grid;
    private List<Franchise> franchiseHistories;
    private List<Franchise> mainFranchises;

    @Autowired
    public FranchiseHistoryView(NavigationManager navigationManager, FranchiseHistoryService franchiseHistoryService) {
        this.navigationManager = navigationManager;
        this.franchiseHistoryService = franchiseHistoryService;
    }

    @PostConstruct
    public void init() {
        setHeightUndefined();
        setMargin(false);

        headline = new Label("Franchise History");
        searchPanel = new Panel();
        searchField = new TextField();
        searchButton = new Button();
        buttonGroup = new HorizontalLayout();
        CheckBox toggleExpandCollapse = new CheckBox("Expand/Collapse all");
        grid = new TreeGrid<>();
        legendLabel = new Label("PA = Playoff Appearances, DT = Division Titles, CT = Conference Titles, LT = LeagueID Titles");
        mainFranchises = new ArrayList<>();

        headline.addStyleName(ValoTheme.LABEL_H1);

        toggleExpandCollapse.addValueChangeListener(event -> toggleExpandCollapse(event.getValue()));
        toggleExpandCollapse.addStyleName(ValoTheme.CHECKBOX_SMALL);
        buttonGroup.addComponent(toggleExpandCollapse);


        grid.addStyleName(ValoTheme.TREETABLE_SMALL);
        grid.setWidth("100%");
        grid.addItemClickListener((ItemClickListener<Franchise>) event -> {
            if (event.getMouseEventDetails().isDoubleClick()) {
                Franchise franchise = event.getItem();
                navigationManager.navigateTo(CommonTeamRosterView.class,franchise.getTeamId() + "&" +
                        franchise.getStartYear() + "&" +
                        franchise.getEndYear() + "&" +
                        franchise.getTeamCity() + " " + franchise.getTeamName());
            }
        });

        //grid.addColumn((ValueProvider<Franchise, Object>) franchise -> rowIndex = (rowIndex % 30) + 1).setCaption("#").setId("#").setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getFranchiseName).setCaption("Franchise");
        grid.addColumn(Franchise::getStartYear).setCaption("From").setWidth(100).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getEndYear).setCaption("To").setWidth(100).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getGames).setCaption("Games").setWidth(100).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getWins).setCaption("Wins").setWidth(100).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getLosses).setCaption("Losses").setWidth(100).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getWinPercentage).setCaption("Win %").setWidth(100).setStyleGenerator(item -> "v-align-left");
        grid.addColumn(Franchise::getPlayoffAppearances).setCaption("PA").setWidth(80).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getDivisionTitles).setCaption("DT").setWidth(80).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getConferenceTitles).setCaption("CT").setWidth(80).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(Franchise::getLeagueTitles).setCaption("LT").setWidth(80).setStyleGenerator(item -> "v-align-right");

        franchiseHistories = franchiseHistoryService.getFranchiseHistory();
        for(Franchise franchise : franchiseHistories) {
            if (franchise.isMainDataSet()) {
                mainFranchises.add(franchise);
            }
        }

        grid.setItems(mainFranchises);
        grid.setHeightByRows(mainFranchises.size());

        for(Franchise franchise : mainFranchises) {
            TreeDataProvider<Franchise> dataProvider = (TreeDataProvider<Franchise>) grid.getDataProvider();

            TreeData<Franchise> data = dataProvider.getTreeData();
            // add new items
            List<Franchise> subfranchises = new ArrayList<>();
            for(Franchise f : franchiseHistories) {
                if (f.getTeamId().equals(franchise.getTeamId()) && !f.isMainDataSet()) {
                    subfranchises.add(f);
                }
            }
            data.addItems(franchise, subfranchises);

            // after adding / removing data, data provider needs to be refreshed
            dataProvider.refreshAll();
        }

        legendLabel.addStyleName(ValoTheme.LABEL_TINY);

        addComponents(headline, buttonGroup, grid, legendLabel);
        setExpandRatio(grid, 1);

    }

    private void toggleExpandCollapse(final Boolean shouldExpand) {
        for(Franchise franchise : mainFranchises) {
            if(shouldExpand) {
                grid.expand(franchise);
            } else {
                grid.collapse(franchise);
            }
        }
    }
}
