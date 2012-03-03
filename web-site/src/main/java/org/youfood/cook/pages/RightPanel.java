package org.youfood.cook.pages;

import com.google.inject.persist.UnitOfWork;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.cook.components.ButtonList;
import org.youfood.cook.events.ButtonListEvent;
import org.youfood.cook.events.OrderEvent;
import org.youfood.cook.listeners.ButtonListListener;
import org.youfood.cook.listeners.OrderListener;
import org.youfood.model.Order;
import org.youfood.services.OrderService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class RightPanel extends Panel implements ButtonListListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightPanel.class);

    private OrderService orderService;
    private List<OrderListener> orderListeners;
    private ButtonList buttonList;

    @Inject
    public RightPanel(OrderService orderService) {
        super("Orders");
        this.orderService = orderService;
        this.orderListeners = new ArrayList<OrderListener>();
        initPanel();
    }

    private void initPanel() {
        setStyleName(Reindeer.PANEL_LIGHT);
        List<Order> orders = orderService.getAllOrder();
        buttonList = new ButtonList();
        buttonList.addButtonListListener(this);
        for (Order order : orders) {
            buttonList.addButton(order.getId(), formatTableId(order.getTableId()));
        }
        addComponent(buttonList);
    }

    @Override
    public void buttonListClick(ButtonListEvent event) {
        Long id = event.getId();
        Order order = orderService.getOrderById(id);
        OrderEvent orderEvent = new OrderEvent(order);
        for (OrderListener orderListener : orderListeners) {
            orderListener.performOrder(orderEvent);
        }
    }

    public void addOrderListener(OrderListener orderListener) {
        orderListeners.add(orderListener);
    }

    public void removeOrderListener(OrderListener orderListener) {
        orderListeners.remove(orderListener);
    }

    private String formatTableId(Integer tableId) {
        return new StringBuilder()
                .append("Table n°")
                .append(tableId)
                .toString();
    }

}