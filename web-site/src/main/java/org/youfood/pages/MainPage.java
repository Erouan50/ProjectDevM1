package org.youfood.pages;

import com.vaadin.Application;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

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
        Window window = new Window();
        window.addComponent(new Label(text));
        setMainWindow(window);
    }
}
