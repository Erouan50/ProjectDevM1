package org.youfood.services;

import org.youfood.model.Category;
import org.youfood.model.Menu;

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

    public List<Menu> getFilteredMenu(String name, Date startDate, Date endDate, Category category) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Menu> criteriaQuery = criteriaBuilder.createQuery(Menu.class);
        Root<Menu> root = criteriaQuery.from(Menu.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        ParameterExpression<Date> d = criteriaBuilder.parameter(Date.class);
        if (name != null) {
            if (!name.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }
        }
        if (startDate != null) {
            predicates.add(criteriaBuilder.equal(root.<Date>get("availableStartDate"), d));
        }
        if (endDate != null) {
            predicates.add(criteriaBuilder.equal(root.<Date>get("availableEndDate"), endDate));
        }
        if (category != null) {
            predicates.add(criteriaBuilder.equal(root.<Category>get("category"), category));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        Query query = em.createQuery(criteriaQuery);
        if (startDate != null) {
            query = em.createQuery(criteriaQuery).setParameter(d, startDate, TemporalType.DATE);
        }
        return query.getResultList();
    }

    public List<Menu> getMenusWeekByDate(Date date) {
        Query query = em.createNamedQuery("findAllMenuBetweenDates");
        query.setParameter("date", date);
        return query.getResultList();
    }
}
