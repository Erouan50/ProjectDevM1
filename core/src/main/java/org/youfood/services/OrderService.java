package org.youfood.services;

import org.youfood.model.Order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class OrderService {

    @PersistenceContext(unitName = "Youfood-PU")
    private EntityManager em;

    @SuppressWarnings(value = "unchecked")
    public List<Order> getAllOrder() {
        Query query = em.createNamedQuery("findAllOrder");
        return query.getResultList();
    }

    public Order getOrderById(Long id) {
        Query query = em.createNamedQuery("findOrderByIdWithMenus");
        query.setParameter("id", id);
        Order order = (Order) query.getSingleResult();
        em.detach(order);
        em.flush();

        return order;
    }
}
