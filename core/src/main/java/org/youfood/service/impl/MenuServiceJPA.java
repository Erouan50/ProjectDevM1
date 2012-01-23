package org.youfood.service.impl;

import com.google.inject.persist.Transactional;
import org.youfood.model.Menu;
import org.youfood.service.MenuService;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MenuServiceJPA implements MenuService {

    @Inject
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addMenu(Menu menu) {
        entityManager.persist(menu);
    }

    @Override
    public Menu getMenuById(Long id) {
        return entityManager.find(Menu.class, id);
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        entityManager.merge(menu);
    }

    @Override
    @Transactional
    public void removeMenu(Menu menu) {
        entityManager.remove(entityManager.merge(menu));
    }
}
