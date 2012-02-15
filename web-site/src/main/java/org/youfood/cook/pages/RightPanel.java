package org.youfood.cook.pages;

import com.google.inject.persist.UnitOfWork;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.youfood.cook.events.OrderEvent;
import org.youfood.cook.listeners.OrderListener;
import org.youfood.model.Order;
import org.youfood.services.OrderService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class RightPanel extends Panel implements Button.ClickListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(RightPanel.class);

    private List<Button> buttons;
    private OrderService orderService;
    private List<OrderListener> orderListeners;

    @Inject
    public RightPanel(OrderService orderService) {
        super("Orders");
        this.orderService = orderService;
        this.orderListeners = new ArrayList<OrderListener>();
        initPanel();
    }

    private void initPanel() {
        setStyleName(Reindeer.PANEL_LIGHT);
        buttons = new ArrayList<Button>();
        List<Order> orders = orderService.getAllOrder();
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        for (Order order : orders) {
            Button button = new Button(order.getId().toString());
            button.setWidth("100%");
            button.setHeight("60px");
            button.addListener(this);
            buttons.add(button);
            verticalLayout.addComponent(button);
        }
        addComponent(verticalLayout);
    }

    @Override
    public void buttonClick(Button.ClickEvent clickEvent) {
        Long id = Long.parseLong(clickEvent.getButton().getCaption());
        LOGGER.debug("Button id: {}", id);
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
}