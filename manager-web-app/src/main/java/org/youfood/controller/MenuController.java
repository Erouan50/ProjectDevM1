package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.AjaxBehaviorListener;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
@ViewScoped
public class MenuController {

    @EJB
    private MenuService menuService;
    @ManagedProperty("#{menuFilterController}")
    private MenuFilterController menuFilterController;
    private File picture;
    private Menu menu;

    public DataModel<Menu> getMenus() {
        if (menuFilterController.isFiltered()) {
            return new ListDataModel<Menu>(menuFilterController.getFilteredMenu());
        } else {
            return new ListDataModel<Menu>(menuService.getAllMenu());
        }
    }

    public String remove(Menu menu) {
        menuService.remove(menu);
        return null;
    }

    public String details(Menu menu) {
        this.menu = menu;
        return null;
    }

    public void setMenuFilterController(MenuFilterController menuFilterController) {
        this.menuFilterController = menuFilterController;
    }

    public File getPicture() {
        return picture;
    }

    public void setPicture(File picture) {
        this.picture = picture;
    }

    public Menu getMenu() {
        return menu;
    }
}
