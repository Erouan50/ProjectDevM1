package org.youfood.cook.pages;

import com.vaadin.Application;
import com.vaadin.ui.*;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author: Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MainPage extends Application {

    @Inject
    @Named("welcome")
    protected String text;

    @Override
    public void init() {
        setTheme("youfood");
        Window window = new Window();
        VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);

        Panel panel = new Panel();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        panel.setContent(verticalLayout);
        panel.setWidth("300px");
        Button button = new Button("Menus");
        button.setWidth("100%");
        button.setHeight("60px");
//        button.setSizeFull();

        verticalLayout.addComponent(button);
//        horizontalLayout.setWidth("300px");
        verticalLayout.setHeight("800px");

        layout.addComponent(panel);
        window.setContent(layout);
        setMainWindow(window);
    }
}