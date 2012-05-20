package org.youfood.services;

import org.youfood.model.Menu;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class MenuService {

    @PersistenceContext
    private EntityManager em;

    public void addMenu(Menu menu) {
        em.persist(menu);
    }

    public Menu getMenuById(Long id) {
        return em.find(Menu.class, id);
    }

    public List<Menu> getAllMenu() {
        Query query = em.createNamedQuery("findAllList");
        return query.getResultList();
    }

    public void updateMenu(Menu menu) {
        em.merge(menu);
    }

    public void removeMenu(Menu menu) {
        em.remove(em.merge(menu));
    }

    public List<Menu> getMenusWeekByDate(Date date) {
        Query query = em.createNamedQuery("findAllMenuBetweenDates");
        query.setParameter("date",date);
        return query.getResultList();
    }
}
