package org.youfood.cook.pages;

import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Window;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MenuSubWindows extends Window {

    public MenuSubWindows() {
        super("Menus");
        setWidth(1024, UNITS_PIXELS);
        setHeight(800, UNITS_PIXELS);
        setModal(true);
        Label test = new Label("C'est un test !");
        Panel component = new MenuIconButton("Test", new ThemeResource("../youfood/img/menu_image_1.jpg"));
        addComponent(test);
        addComponent(component);
    }
}
