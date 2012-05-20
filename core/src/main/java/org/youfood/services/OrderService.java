package org.youfood.services;

import org.youfood.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class OrderService {

    @PersistenceContext
    private EntityManager em;

    public List<Order> getAllOrder() {
        Query query = em.createNamedQuery("findAllOrder");
        return query.getResultList();
    }

    public Order getOrderById(Long id) {
        return em.find(Order.class, id);
    }
}
