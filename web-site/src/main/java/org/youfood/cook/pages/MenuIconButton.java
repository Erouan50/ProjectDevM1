package org.youfood.cook.pages;

import com.vaadin.terminal.Resource;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;

import javax.inject.Inject;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MenuIconButton extends Panel {

    private String title;
    private Resource image;

    @Inject
    public MenuIconButton(String title, Resource image) {
        this.title = title;
        this.image = image;
        init();
    }

    private void init() {

        setStyleName(Reindeer.PANEL_LIGHT);
        HorizontalLayout layout = new HorizontalLayout();
        Label text = new Label(title);
        layout.setMargin(true);
        layout.addComponent(new Embedded("Test", image));
        layout.addComponent(text);
        addComponent(layout);
    }
}
