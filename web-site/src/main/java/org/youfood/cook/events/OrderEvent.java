package org.youfood.cook.events;

import org.youfood.model.Order;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class OrderEvent {

    private Order order;

    public OrderEvent(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }
}
