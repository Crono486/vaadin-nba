package com.example.vaadin.nba.ui.view;

import com.example.vaadin.nba.backend.data.DraftHistory;
import com.example.vaadin.nba.backend.service.DraftHistoryService;
import com.example.vaadin.nba.backend.util.CalendarUtil;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.example.vaadin.nba.ui.view.player.CommonPlayerInfoView;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.TreeGrid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@SpringView
public class DraftHistoryView extends VerticalLayout implements View {
    private final NavigationManager navigationManager;
    private final DraftHistoryService draftHistoryService;

    private Panel searchPanel;
    private TextField searchField;
    private Button searchButton;
    private Label labelHeadline;
    private ComboBox<Integer> comboSeason;
    private HorizontalLayout buttonGroup;
    private TreeGrid<DraftHistory> grid;
    private Label legendLabel;
    private List<DraftHistory> draftHistory;

    protected float buttonWidth = 37;

    @Autowired
    public DraftHistoryView(NavigationManager navigationManager, DraftHistoryService draftHistoryService) {
        this.navigationManager = navigationManager;
        this.draftHistoryService = draftHistoryService;
    }

    @PostConstruct
    public void init() {
        setHeightUndefined();
        setMargin(false);

        labelHeadline = new Label("Draft History");
        comboSeason = new ComboBox<>("Season");
        searchPanel = new Panel();
        searchField = new TextField();
        searchButton = new Button();
        buttonGroup = new HorizontalLayout();
        grid = new TreeGrid<>();
        legendLabel = new Label("OVR = Overall, R = Round");

        labelHeadline.addStyleName(ValoTheme.LABEL_H1);

        ArrayList<Integer> seasons = new ArrayList<>();
        int currentSeason = CalendarUtil.getCurrentSeason();
        for(int i = currentSeason; i >= 1947 ; i--) {
            seasons.add(i);
        }
        comboSeason.setEmptySelectionAllowed(false);
        comboSeason.setItems(seasons);
        comboSeason.setValue(currentSeason);
        comboSeason.addValueChangeListener(event -> draft(event.getValue()));

        grid.addStyleName(ValoTheme.TREETABLE_SMALL);
        grid.setWidth("100%");
        grid.setHeightByRows(60);

        grid.addColumn(DraftHistory::getOverallPick).setCaption("OVR #").setWidth(90).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(DraftHistory::getRoundNumber).setCaption("R").setWidth(70).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(DraftHistory::getRoundPick).setCaption("R #").setWidth(70).setStyleGenerator(item -> "v-align-right");
        grid.addColumn(player -> "i", new ButtonRenderer<>(event -> {
            DraftHistory draftHistory = event.getItem();
            navigationManager.navigateTo(CommonPlayerInfoView.class, draftHistory.getPersonId());
        })).setWidth(buttonWidth);
        grid.addColumn(DraftHistory::getPlayerName).setCaption("Player Name");
        grid.addColumn(DraftHistory::getFranchiseName).setCaption("Franchise Name").setWidth(320);
        grid.addColumn(DraftHistory::getTeamAbbreviation).setCaption("Abbr.").setWidth(90);
        grid.addColumn(DraftHistory::getOrganization).setCaption("Organization").setWidth(180);
        grid.addColumn(DraftHistory::getOrganizationType).setCaption("Organization Category").setWidth(200);

        draftHistory = draftHistoryService.getDraftHistory(comboSeason.getValue());

        grid.setItems(draftHistory);

        legendLabel.addStyleName(ValoTheme.LABEL_TINY);

        addComponents(labelHeadline, comboSeason, buttonGroup, grid, legendLabel);
        setExpandRatio(grid, 1);

    }

    private void draft(Integer season) {

        draftHistory = draftHistoryService.getDraftHistory(season);

        TreeDataProvider<DraftHistory> dataProvider = (TreeDataProvider<DraftHistory>) grid.getDataProvider();
        TreeData<DraftHistory> data = dataProvider.getTreeData();

        data.clear();
        data.addItems(null, draftHistory);

        // after adding / removing data, data provider needs to be refreshed
        dataProvider.refreshAll();
    }
}
