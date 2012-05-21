package org.youfood.controller;

import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    private List<Menu> menus;

    public List<Menu> getMenus() {
        if (menus == null) {
            menus = menuService.getAllMenu();
        }
        Logger.getLogger(MenuController.class.getName()).log(Level.WARNING, "Menus size: " + menus.size());
        System.out.println("toto");
        return menus;
    }
}
