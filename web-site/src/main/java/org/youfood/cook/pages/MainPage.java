package org.youfood.cook.pages;

import com.vaadin.Application;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MainPage extends Application implements Button.ClickListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainPage.class);

    @Inject
    @Named("welcome")
    protected String text;

    @Inject
    private RightPanel rightPanel;
    @Inject
    private CenterPanel centerPanel;
    @Inject
    private MenuService menuService;

    public MainPage() {

    }

    @Override
    public void init() {
        setTheme("youfood");
        Window window = new Window();
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        MenuMainPage menuMainPage = new MenuMainPage();
        menuMainPage.setWidth("100%");

        rightPanel.setWidth("100%");
        rightPanel.setHeight("800px");

        centerPanel.setWidth("100%");
        centerPanel.setHeight("800px");
        rightPanel.addOrderListener(centerPanel);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);
        horizontalLayout.setWidth("100%");
        horizontalLayout.addComponent(rightPanel);
        horizontalLayout.setComponentAlignment(rightPanel, Alignment.MIDDLE_LEFT);
        horizontalLayout.setExpandRatio(rightPanel, 0.5F);
        horizontalLayout.addComponent(centerPanel);
        horizontalLayout.setComponentAlignment(centerPanel, Alignment.MIDDLE_RIGHT);
        horizontalLayout.setExpandRatio(centerPanel, 3.0F);

        layout.addComponent(menuMainPage);
        layout.addComponent(horizontalLayout);

        window.setContent(layout);
        setMainWindow(window);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        Date date = new GregorianCalendar(2012, 3, 6).getTime();
        List<Menu> menus = menuService.getMenusWeekByDate(date);
        LOGGER.debug("List menu {}", menus);
    }
}