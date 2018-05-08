package com.example.vaadin.nba.ui;

import com.example.vaadin.nba.app.HasLogger;
import com.example.vaadin.nba.ui.navigation.NavigationManager;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.Viewport;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@Theme("nba")
@SpringUI
@Viewport("width=device-width,initial-scale=1.0,user-scalable=no")
@Title("Vaadin NBA App")
public class AppUI extends UI implements HasLogger {

    private final NavigationManager navigationManager;
    private final MainView mainView;

    @Autowired
    public AppUI(NavigationManager navigationManager, MainView mainView) {
        this.navigationManager = navigationManager;
        this.mainView = mainView;
    }

    @Override
    protected void init(VaadinRequest request) {
        setErrorHandler(event -> {
            Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
            getLogger().error("Error during request", t);
        });

        setContent(mainView);

        navigationManager.navigateToDefaultView();
    }
}
