package org.youfood.controller;

import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class MenuController {

    @EJB
    private MenuService menuService;
    @ManagedProperty("#{menuFilterController}")
    private MenuFilterController menuFilterController;
    private List<Menu> menus;

    private Menu menu;

    public String addMenu() {
        menuService.addMenu(menu);
        return "/auth/manager_home?faces-redirect=true";
    }

    public List<Menu> getMenus() {
        if (menuFilterController.isFiltered()) {
            menus = menuFilterController.getFilteredMenu();
        } else {
            menus = menuService.getAllMenu();
        }
        return menus;
    }

    public Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void setMenuFilterController(MenuFilterController menuFilterController) {
        this.menuFilterController = menuFilterController;
    }
}
