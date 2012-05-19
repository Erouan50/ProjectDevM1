package org.youfood.services.impl;

import org.youfood.dao.MenuDao;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class DefaultMenuService implements MenuService {

    @Inject
    private MenuDao menuDao;

    @Override
    public void addMenu(Menu menu) {
        menuDao.addMenu(menu);
    }

    @Override
    public Menu getMenuById(Long id) {
        return menuDao.getMenuById(id);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.updateMenu(menu);
    }

    @Override
    public void removeMenu(Menu menu) {
        menuDao.removeMenu(menu);
    }

    @Override
    public List<Menu> getMenusWeekByDate(Date date) {
        return menuDao.getMenusWeekByDate(date);
    }
}
