package com.example.vaadin.nba.ui.view.player;

import com.example.vaadin.nba.backend.data.CommonPlayerInfo;
import com.example.vaadin.nba.backend.service.CommonPlayerInfoService;
import com.example.vaadin.nba.backend.util.CalendarUtil;
import com.example.vaadin.nba.backend.util.Constants;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Locale;

@SpringView
public class CommonPlayerInfoView extends VerticalLayout implements View {

    private final NavigationManager navigationManager;
    private final CommonPlayerInfoService commonPlayerInfoService;

    private Panel panelCommonPlayerInfo;
    private Image headShot;
    private Image teamLogo;
    private Label jersey;
    private Label firstName;
    private Label lastName;
    private Label position;
    private Label teamName;
    HashMap<PlayerStatsEnum, Label> mapLabelPlayerStats;
    private CommonPlayerInfo commonPlayerInfo;
    private String playerId;

    private DecimalFormatSymbols dfs = new DecimalFormatSymbols(Locale.US);
    private DecimalFormat df = new DecimalFormat("0.0##", dfs);

    public CommonPlayerInfoView(NavigationManager navigationManager, CommonPlayerInfoService commonPlayerInfoService) {
        this.navigationManager = navigationManager;
        this.commonPlayerInfoService = commonPlayerInfoService;
    }

    @PostConstruct
    public void init() {
        setSizeFull();
        setMargin(false);

        // Initializing Components like Images, Labels, etc.
        headShot = new Image();
        teamLogo = new Image();
        jersey = new Label();
        firstName = new Label();
        lastName = new Label();
        position = new Label();
        teamName = new Label();

        // Base Layouting
        panelCommonPlayerInfo = new Panel();
        VerticalLayout vlPlayerSummary = new VerticalLayout();
        vlPlayerSummary.setMargin(false);
        vlPlayerSummary.setSpacing(false);
        panelCommonPlayerInfo.addStyleName("common-player-info");
        panelCommonPlayerInfo.setContent(vlPlayerSummary);

        // Player Summary Inner Layouting
        Panel panelPlayerSummaryInner = new Panel();
        panelPlayerSummaryInner.addStyleName("teamlogo-gradient");
        panelPlayerSummaryInner.addStyleName("common-player-info");

        HorizontalLayout hlPlayerSummaryInner = new HorizontalLayout();
        hlPlayerSummaryInner.setMargin(false);
        HorizontalLayout hlPlayerSummaryInnerInfo = new HorizontalLayout();
        hlPlayerSummaryInnerInfo.setMargin(false);
        VerticalLayout vlPlayerSummaryInnerInfoText = new VerticalLayout();
        vlPlayerSummaryInnerInfoText.setMargin(false);
        HorizontalLayout hlPlayerSummaryInnerInfoTextTop = new HorizontalLayout();
        hlPlayerSummaryInnerInfoTextTop.setMargin(false);
        HorizontalLayout hlPlayerSummaryInnerInfoTextBottom = new HorizontalLayout();
        hlPlayerSummaryInnerInfoTextBottom.setMargin(false);
        VerticalLayout vlPlayerSummaryInnerInfoTextTopName = new VerticalLayout();
        vlPlayerSummaryInnerInfoTextTopName.setMargin(false);
        vlPlayerSummaryInnerInfoTextTopName.setSpacing(false);

        // Player Stats Layouting
        Panel panelPlayerStats = new Panel();
        panelPlayerStats.addStyleName("common-player-info");
        GridLayout gridPlayerStats = new GridLayout(8, 2);
        gridPlayerStats.setMargin(false);
        gridPlayerStats.setSpacing(false);
        gridPlayerStats.setWidth("100%");

        mapLabelPlayerStats = new HashMap<>();
        HashMap<PlayerStatsEnum, VerticalLayout> mapVlPlayerStats= new HashMap<>();
        HashMap<PlayerStatsEnum, Panel> mapPanelPlayerStats= new HashMap<>();
        for(int i = 0; i < 11; i++) {
            Label labelPlayerStats = new Label();
            Label valuePlayerStats = new Label();
            labelPlayerStats.addStyleName("v-label-microscopic");
            VerticalLayout vl = new VerticalLayout();
            vl.setMargin(false);
            vl.setSpacing(false);
            vl.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
            vl.addComponents(labelPlayerStats, valuePlayerStats);
            vl.setComponentAlignment(labelPlayerStats, Alignment.TOP_LEFT);
            vl.setSizeFull();
            Panel panel = new Panel();
            panel.addStyleName("common-player-info");
            panel.setContent(vl);
            PlayerStatsEnum key = null;
            switch (i) {
                case 0:
                    key = PlayerStatsEnum.HEIGHT;
                    gridPlayerStats.addComponent(panel, 0, 0, 0, 0);
                    break;
                case 1:
                    key = PlayerStatsEnum.WEIGHT;
                    gridPlayerStats.addComponent(panel, 1, 0, 1, 0);
                    break;
                case 2:
                    key = PlayerStatsEnum.AGE;
                    gridPlayerStats.addComponent(panel, 0, 1, 0, 1);
                    break;
                case 3:
                    key = PlayerStatsEnum.BIRTHDAY;
                    gridPlayerStats.addComponent(panel, 1, 1, 1, 1);
                    break;
                case 4:
                    key = PlayerStatsEnum.PRIOR;
                    gridPlayerStats.addComponent(panel, 2, 0, 3, 0);
                    break;
                case 5:
                    key = PlayerStatsEnum.DRAFT;
                    gridPlayerStats.addComponent(panel, 2, 1, 2, 1);
                    break;
                case 6:
                    key = PlayerStatsEnum.EXP;
                    gridPlayerStats.addComponent(panel, 3, 1, 3, 1);
                    break;
                case 7:
                    key = PlayerStatsEnum.PTS;
                    gridPlayerStats.addComponent(panel, 4, 0, 4, 1);
                    break;
                case 8:
                    key = PlayerStatsEnum.REB;
                    gridPlayerStats.addComponent(panel, 5, 0, 5, 1);
                    break;
                case 9:
                    key = PlayerStatsEnum.AST;
                    gridPlayerStats.addComponent(panel, 6, 0, 6, 1);
                    break;
                case 10:
                    key = PlayerStatsEnum.PIE;
                    gridPlayerStats.addComponent(panel, 7, 0, 7, 1);
                    break;
            }
            if(i >= 7 && i <= 10) {
                valuePlayerStats.addStyleName(ValoTheme.LABEL_HUGE);
                panel.setSizeFull();
            }
            labelPlayerStats.setValue(key.toString());
            vl.setExpandRatio(labelPlayerStats, 1);
            vl.setExpandRatio(valuePlayerStats, 99);
            mapLabelPlayerStats.put(key, valuePlayerStats);
            mapVlPlayerStats.put(key, vl);
            mapPanelPlayerStats.put(key, panel);
        }

        teamLogo.addStyleName("teamlogo-small");
        jersey.addStyleName("v-label-gigantic");
        jersey.addStyleName(ValoTheme.LABEL_BOLD);
        firstName.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        firstName.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        lastName.addStyleName(ValoTheme.LABEL_BOLD);

        hlPlayerSummaryInnerInfoTextBottom.addComponents(position, teamName);
        vlPlayerSummaryInnerInfoTextTopName.addComponents(firstName, lastName);
        hlPlayerSummaryInnerInfoTextTop.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        hlPlayerSummaryInnerInfoTextTop.addComponents(jersey, vlPlayerSummaryInnerInfoTextTopName);
        vlPlayerSummaryInnerInfoText.addComponents(hlPlayerSummaryInnerInfoTextTop, hlPlayerSummaryInnerInfoTextBottom);
        hlPlayerSummaryInnerInfo.setDefaultComponentAlignment(Alignment.BOTTOM_CENTER);
        hlPlayerSummaryInnerInfo.addComponents(teamLogo, vlPlayerSummaryInnerInfoText);
        hlPlayerSummaryInner.addComponents(headShot, hlPlayerSummaryInnerInfo);
        panelPlayerSummaryInner.setContent(hlPlayerSummaryInner);
        panelPlayerStats.setContent(gridPlayerStats);
        vlPlayerSummary.addComponents(panelPlayerSummaryInner, panelPlayerStats);
        addComponent(panelCommonPlayerInfo);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        playerId = event.getParameters();
        commonPlayerInfo = commonPlayerInfoService.getCommonPlayerInfo(playerId);
        headShot.setSource(new ExternalResource(getHeadShot(commonPlayerInfo.getTeamId(), commonPlayerInfo.getPersonId())));
        teamLogo.setSource(new ExternalResource(getTeamLogo(commonPlayerInfo.getTeamAbbreviation())));
        jersey.setValue("#" + commonPlayerInfo.getJersey());
        firstName.setValue(commonPlayerInfo.getFirstName());
        lastName.setValue(commonPlayerInfo.getLastName());
        position.setValue(commonPlayerInfo.getPosition());
        teamName.setValue(commonPlayerInfo.getTeamCity() + " " + commonPlayerInfo.getTeamName());
        String abbr = commonPlayerInfo.getTeamAbbreviation();
        String teamlogoGradientStyle = ".nba .teamlogo-gradient::after {" +
                "background: url(https://stats.nba.com/media/img/teams/logos/" + abbr + "_logo.svg) center center/100% no-repeat;" +
                "background-image: url(https://stats.nba.com/media/img/teams/logos/" + abbr + "_logo.svg);" +
                "}";
        Page.getCurrent().getStyles().add(teamlogoGradientStyle);
        mapLabelPlayerStats.get(PlayerStatsEnum.HEIGHT).setValue(getHeightInCm(commonPlayerInfo.getHeight()) + " cm");
        mapLabelPlayerStats.get(PlayerStatsEnum.WEIGHT).setValue(getWeightInKg(commonPlayerInfo.getWeight()) + " kg");
        mapLabelPlayerStats.get(PlayerStatsEnum.AGE).setValue(CalendarUtil.getAgeString(commonPlayerInfo.getBirthDate()));
        mapLabelPlayerStats.get(PlayerStatsEnum.BIRTHDAY).setValue(CalendarUtil.getBirthdayString(commonPlayerInfo.getBirthDate()));
        mapLabelPlayerStats.get(PlayerStatsEnum.PRIOR).setValue(commonPlayerInfo.getLastAffiliation());
        if(commonPlayerInfo.getDraftYear().equalsIgnoreCase("undrafted")) {
            mapLabelPlayerStats.get(PlayerStatsEnum.DRAFT).setValue(commonPlayerInfo.getDraftYear());
        } else {
            mapLabelPlayerStats.get(PlayerStatsEnum.DRAFT).setValue(commonPlayerInfo.getDraftYear() + " Rnd " + commonPlayerInfo.getDraftRound() + " #" + commonPlayerInfo.getDraftNumber());
        }
        mapLabelPlayerStats.get(PlayerStatsEnum.EXP).setValue(Integer.valueOf(commonPlayerInfo.getToYear()) - Integer.valueOf(commonPlayerInfo.getFromYear()) + " yrs");
        mapLabelPlayerStats.get(PlayerStatsEnum.PTS).setValue(getFormattedDecimal(commonPlayerInfo.getHeadlinePts()));
        mapLabelPlayerStats.get(PlayerStatsEnum.REB).setValue(getFormattedDecimal(commonPlayerInfo.getHeadlineReb()));
        mapLabelPlayerStats.get(PlayerStatsEnum.AST).setValue(getFormattedDecimal(commonPlayerInfo.getHeadlineAst()));
        mapLabelPlayerStats.get(PlayerStatsEnum.PIE).setValue(getFormattedDecimal(commonPlayerInfo.getHeadlinePie(), 100));
    }

    private String getHeadShot(String teamId, String playerId) {
        return String.format(Constants.HEADSHOT_URL, playerId);
    }

    private String getTeamLogo(String teamAbbreviation) {
        return String.format(Constants.TEAMLOGO_URL, teamAbbreviation);
    }

    private int getWeightInKg(String weightInLbs) {
        float factor = 0.453592f;
        return Math.round(Integer.valueOf(weightInLbs) * factor);
    }

    private int getHeightInCm(String heightInFt) {
        float factor = 2.54f;
        int feet = Integer.valueOf(heightInFt.substring(0, 1));
        int inches = Integer.valueOf(heightInFt.substring(2));
        int weightInInches = 12*feet+inches;
        return Math.round(weightInInches * factor);
    }

    private String getFormattedDecimal(String decimal) {
        return getFormattedDecimal(decimal, 1);
    }

    private String getFormattedDecimal(String decimal, int factor) {
        return df.format(Double.valueOf(decimal)*factor);
    }
}
