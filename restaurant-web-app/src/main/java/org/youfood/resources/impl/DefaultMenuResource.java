package org.youfood.resources.impl;

import org.youfood.model.Menu;
import org.youfood.resources.MenuResource;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class DefaultMenuResource implements MenuResource {

    @EJB
    private MenuService menuService;

    @Override
    public List<Menu> getAllMenus() {
        return menuService.getAllMenu();
    }

    @Override
    public Menu getMenuById(@PathParam("id") Long id) {
        return menuService.getMenuById(id);
    }
}
