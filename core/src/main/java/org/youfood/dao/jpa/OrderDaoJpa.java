package org.youfood.dao.jpa;

import org.youfood.dao.OrderDao;
import org.youfood.model.Order;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class OrderDaoJpa implements OrderDao {

    @Inject
    private Provider<EntityManager> provider;

    @Override
    public List<Order> getAllOrder() {
        EntityManager em = provider.get();
        Query query = em.createNamedQuery("findAllOrder");
        return query.getResultList();
    }

    @Override
    public Order getOrderById(Long id) {
        EntityManager em = provider.get();
        return em.find(Order.class, id);
    }
}
