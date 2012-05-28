package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class MenuFilterController {

    @EJB
    private MenuService menuService;
    private String name;
    private Date startDate;
    private Date endDate;
    private Category category;
    private List<Menu> filteredMenu;

    public boolean isFiltered() {
        return name != null || startDate != null || endDate != null || category != null;
    }

    public List<Menu> getFilteredMenu() {
        if (filteredMenu == null) {
            filteredMenu = menuService.getFilteredMenu(name, startDate, endDate, category);
        }
        return filteredMenu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
