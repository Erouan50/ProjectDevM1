package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.services.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class CategoryController {

    @EJB
    private CategoryService categoryService;
    private Category category;

    public String addCategory() {
        categoryService.addCategory(category);
        return "/auth/list_categories?faces-redirect=true";
    }

    public Category getCategory() {
        if (category == null) {
            category = new Category();
        }
        return category;
    }
}
