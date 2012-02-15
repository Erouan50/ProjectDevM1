package org.youfood.dao;

import org.youfood.model.Order;

import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface OrderDao {

    List<Order> getAllOrder();

    Order getOrderById(Long id);
}
