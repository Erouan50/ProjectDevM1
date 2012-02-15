package org.youfood.services;

import com.google.inject.servlet.RequestScoped;
import org.youfood.model.Order;

import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface OrderService {

    List<Order> getAllOrder();

    Order getOrderById(Long id);
}
