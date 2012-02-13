package org.youfood.dao;

import org.youfood.model.Menu;

import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public interface MenuDao {

    void addMenu(Menu menu);

    Menu getMenuById(Long id);

    List<Menu> getAllMenu();

    void updateMenu(Menu menu);

    void removeMenu(Menu menu);
}
