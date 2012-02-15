package org.youfood.cook.listeners;

import org.youfood.cook.events.OrderEvent;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface OrderListener {

    public void performOrder(OrderEvent orderEvent);
}
