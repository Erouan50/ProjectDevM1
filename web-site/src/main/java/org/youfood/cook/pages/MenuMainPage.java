package org.youfood.cook.pages;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MenuMainPage extends Panel implements Button.ClickListener{

    public MenuMainPage() {
        init();
    }

    private void init() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setMargin(true);
        horizontalLayout.setSpacing(true);

        Button button = new Button("Menus");
        button.setWidth("325px");
        button.setHeight("60px");
        button.addListener(this);

        horizontalLayout.addComponent(button);
        setContent(horizontalLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        getWindow().addWindow(new MenuSubWindows());
    }
}