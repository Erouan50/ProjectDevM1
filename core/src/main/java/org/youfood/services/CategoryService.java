package org.youfood.services;

import org.youfood.model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class CategoryService {

    @PersistenceContext
    private EntityManager em;

    public void addCategory(Category category) {
        em.persist(category);
    }

    @SuppressWarnings(value = "unchecked")
    public List<Category> getAllCategories() {
        Query query = em.createNamedQuery("findAllCategories", Category.class);
        return query.getResultList();
    }

    public Category getCategoryById(Long id) {
        return em.find(Category.class, id);
    }
}
