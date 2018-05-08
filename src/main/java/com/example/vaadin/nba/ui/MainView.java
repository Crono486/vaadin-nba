package com.example.vaadin.nba.ui;

import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.example.vaadin.nba.ui.view.dashboard.DashboardView;
import com.example.vaadin.nba.ui.view.DraftHistoryView;
import com.example.vaadin.nba.ui.view.FranchiseHistoryView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@SpringViewDisplay
@UIScope
public class MainView extends HorizontalLayout implements ViewDisplay {

    private Label activeViewName;
    private CssLayout navigation;
    private Button dashboard;
    private Button franchisehistory;
    private Button drafthistory;
    private Panel panel;
    private VerticalLayout content;

    private final Map<Class<? extends View>, Button> navigationButtons = new HashMap<>();
    private final NavigationManager navigationManager;

    @Autowired
    public MainView(NavigationManager navigationManager) {
        this.navigationManager = navigationManager;
    }

    @PostConstruct
    public void init() {
        activeViewName = new Label("activeViewName");
        dashboard = new Button("Dashboard");
        franchisehistory = new Button("Franchise History");
        drafthistory = new Button("Draft History");
        navigation = new CssLayout();
        panel = new Panel();
        content = new VerticalLayout();

        navigation.setWidth("14" + Unit.EM);
        navigation.setPrimaryStyleName(ValoTheme.MENU_ROOT);
        Image logo = new Image(null, new ThemeResource("logo.png"));
        logo.addStyleName("logo");
        logo.addClickListener(e -> navigationManager.navigateTo(DashboardView.class));
        navigation.addComponents(logo, dashboard, franchisehistory, drafthistory);
        for(int i = 0; i < navigation.getComponentCount(); i++) {
            navigation.getComponent(i).setPrimaryStyleName(ValoTheme.MENU_ITEM);
        }

        panel.setSizeFull();
        panel.addStyleName(ValoTheme.PANEL_BORDERLESS);

        addComponents(navigation, panel);
        panel.setContent(content);
        setSizeFull();
        setExpandRatio(panel, 1);
        attachNavigation(dashboard, DashboardView.class);
        attachNavigation(franchisehistory, FranchiseHistoryView.class);
        attachNavigation(drafthistory, DraftHistoryView.class);
    }

    /**
     * Makes clicking the given button navigate to the given view if the user
     * has access to the view.
     * <p>
     * If the user does not have access to the view, hides the button.
     *
     * @param navigationButton
     *            the button to use for navigatio
     * @param targetView
     *            the view to navigate to when the user clicks the button
     */
    private void attachNavigation(Button navigationButton, Class<? extends View> targetView) {
        navigationButton.setVisible(true);
        navigationButtons.put(targetView, navigationButton);
        navigationButton.addClickListener(e -> navigationManager.navigateTo(targetView));
    }

    @Override
    public void showView(View view) {
        content.removeAllComponents();
        content.addComponent(view.getViewComponent());

        navigationButtons.forEach((viewClass, button) -> button.setStyleName("selected", viewClass == view.getClass()));

        Button menuItem = navigationButtons.get(view.getClass());
        String viewName = "";
        if(menuItem != null) {
            viewName = menuItem.getCaption();
        }
        activeViewName.setValue(viewName);
    }
}
