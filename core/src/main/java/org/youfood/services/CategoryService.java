package org.youfood.services;

import org.youfood.model.Category;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
