package org.youfood.services;

import org.youfood.model.Category;
import org.youfood.model.Menu;
import org.youfood.model.Menu_;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class MenuService {

    @PersistenceContext(unitName = "Youfood-PU")
    private EntityManager em;

    public void addMenu(Menu menu) {
        em.persist(menu);
    }

    public Menu getMenuById(Long id) {
        return em.find(Menu.class, id);
    }

    @SuppressWarnings(value = "unchecked")
    public List<Menu> getAllMenu() {
        Query query = em.createNamedQuery("findAllMenu");
        return query.getResultList();
    }

    public void updateMenu(Menu menu) {
        em.merge(menu);
    }

    public void removeMenu(Menu menu) {
        em.remove(em.merge(menu));
    }

    @SuppressWarnings(value = "unchecked")
    public List<Menu> getFilteredMenu(String name, Date startDate, Date endDate, Category category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> q = cb.createQuery(Menu.class);
        Root<Menu> menu = q.from(Menu.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (name != null && !name.isEmpty()) {
            predicates.add(cb.equal(menu.get(Menu_.name), name));
        }
        if (startDate != null) {
            predicates.add(cb.equal(menu.<Date>get(Menu_.availableStartDate), startDate));
        }
        if (endDate != null) {
            predicates.add(cb.equal(menu.<Date>get(Menu_.availableEndDate), endDate));
        }
        if (category != null) {
            predicates.add(cb.equal(menu.<Category>get(Menu_.category), category));
        }
        q.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        q.orderBy(cb.desc(menu.get(Menu_.availableStartDate)));
        Query query = em.createQuery(q);
        return query.getResultList();
    }

    @SuppressWarnings(value = "unchecked")
    public List<Menu> getMenusWeekByDate(Date date) {
        Query query = em.createNamedQuery("findAllMenuBetweenDates");
        query.setParameter("date", date);
        return query.getResultList();
    }

    @SuppressWarnings(value = "unchecked")
    public List<Menu> getMenusByCategory(Category category) {
        Query query = em.createNamedQuery("findAllMenuByCategory");
        query.setParameter("category", category);
        return query.getResultList();
    }

    public void remove(Menu menu) {
        em.remove(em.merge(menu));
    }
}
