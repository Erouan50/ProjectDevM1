package org.youfood.services.impl;

import org.youfood.dao.OrderDao;
import org.youfood.model.Order;
import org.youfood.services.OrderService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class DefaultOrderService implements OrderService {

    @Inject
    private OrderDao orderDao;

    @Override
    public List<Order> getAllOrder() {
        return orderDao.getAllOrder();
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}
