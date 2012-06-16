package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
@ViewScoped
public class AddMenuController implements ValueChangeListener, Serializable{

    @EJB
    private MenuService menuService;

    private Menu menu;
    private Category category;

    public String addMenu() {
        menu.setId(null);
        menuService.addMenu(menu);
        return "/auth/manager_home?faces-redirect=true";
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Menu getMenu() {
        if (menu == null) {
            menu = new Menu();
        }
        return menu;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public List<SelectItem> getMenuItems() {
        List<SelectItem> menuItems = new ArrayList<SelectItem>();
        if (category != null) {
            menuItems.add(new SelectItem(null, "None"));
            for (Menu menu : menuService.getMenusByCategory(category)) {
                menuItems.add(new SelectItem(menu, menu.getName()));
            }
        }
        return menuItems;
    }

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        if (event.getNewValue() == null) {
            menu = null;
        }
    }
}
