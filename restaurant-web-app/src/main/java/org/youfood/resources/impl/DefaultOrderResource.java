package org.youfood.resources.impl;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.youfood.model.Order;
import org.youfood.resources.OrderResource;
import org.youfood.services.NotificationSender;
import org.youfood.services.OrderService;
import org.youfood.utils.RabbitMQConfiguration;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class DefaultOrderResource implements OrderResource {

    @EJB
    private OrderService orderService;

    @Override
    public List<Order> getAllOrders() {
        return orderService.getAllOrder();
    }

    @Override
    public Order getOrder(Long id) {
        return orderService.getOrderById(id);
    }

    @Override
    public Order updateOrder(Order order) {
        Order orderBase = orderService.getOrderById(order.getId());
        orderBase.setStatus(order.getStatus());
        orderService.updateOrder(orderBase);
        return order;
    }

    @Override
    public void addOrder(String order) {
        new NotificationSender().sendOrder(order);
    }

}
