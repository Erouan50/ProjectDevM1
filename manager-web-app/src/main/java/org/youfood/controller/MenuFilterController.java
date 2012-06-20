package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.Date;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
@ViewScoped
public class MenuFilterController {

    @EJB
    private MenuService menuService;
    private String name;
    private Date startDate;
    private Date endDate;
    private Category category;

    public String filter() {
        return null;
    }

    public boolean isFiltered() {
        return startDate != null || endDate != null || category != null || name != null && !name.isEmpty();
    }

    public List<Menu> getFilteredMenu() {
        return menuService.getFilteredMenu(name, startDate, endDate, category);
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
