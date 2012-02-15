package org.youfood.cook.pages;

import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;
import org.youfood.cook.events.OrderEvent;
import org.youfood.cook.listeners.OrderListener;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class CenterPanel extends Panel implements OrderListener {

    private Label label;

    public CenterPanel() {
        super("Order");
        init();
    }

    public void init() {
        setStyleName(Reindeer.PANEL_LIGHT);
        label = new Label("Button id: ");
        addComponent(label);
    }

    @Override
    public void performOrder(OrderEvent orderEvent) {
        label.setValue("Button id: " + orderEvent.getOrder().getId());
    }
}
