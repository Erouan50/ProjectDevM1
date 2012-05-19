package org.youfood.dao.jpa;

import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import org.youfood.dao.MenuDao;
import org.youfood.model.Menu;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class MenuDaoJpa implements MenuDao {

    @Inject
    private Provider<EntityManager> entityManager;

    @Override
    @Transactional
    public void addMenu(Menu menu) {
        entityManager.get().persist(menu);
    }

    @Override
    public Menu getMenuById(Long id) {
        return entityManager.get().find(Menu.class, id);
    }

    @Override
    public List<Menu> getAllMenu() {
        Query query = entityManager.get().createNamedQuery("findAllList");
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateMenu(Menu menu) {
        entityManager.get().merge(menu);
    }

    @Override
    @Transactional
    public void removeMenu(Menu menu) {
        entityManager.get().remove(entityManager.get().merge(menu));
    }

    @Override
    public List<Menu> getMenusWeekByDate(Date date) {
        Query query = entityManager.get().createNamedQuery("findAllMenuBetweenDates");
        query.setParameter("date",date);
        return query.getResultList();
    }
}
