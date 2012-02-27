package org.youfood.cook.pages;

import com.google.inject.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.themes.Reindeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.cook.events.OrderEvent;
import org.youfood.cook.listeners.OrderListener;
import org.youfood.model.Order;

import javax.inject.Inject;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class CenterPanel extends Panel implements OrderListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(CenterPanel.class);

    private Label label;

    private ArticlesOrderPanel articlesOrderPanel;

    @Inject
    public CenterPanel(ArticlesOrderPanel articlesOrderPanel) {
        super("Order");
        this.articlesOrderPanel = articlesOrderPanel;
        init();
    }

    public void init() {
        setStyleName(Reindeer.PANEL_LIGHT);
        label = new Label("Button id: ");
        addComponent(label);
        addComponent(articlesOrderPanel);
    }

    @Override
    public void performOrder(OrderEvent orderEvent) {
        final Order order = orderEvent.getOrder();
        setCaption("Order id: " + order.getId());
        articlesOrderPanel.setOrder(order);
        label.setValue("Button id: " + orderEvent.getOrder().getId());
        setVisible(true);
    }
}