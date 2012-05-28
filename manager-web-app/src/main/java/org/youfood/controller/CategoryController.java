package org.youfood.controller;

import org.youfood.model.Category;
import org.youfood.services.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class CategoryController {

    @EJB
    private CategoryService categoryService;
    private Category category;
    private List<Category> categories;
    private List<SelectItem> categoryItems;

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

    public List<Category> getCategories() {
        if (categories == null) {
            categories = categoryService.getAllCategories();
        }
        return categories;
    }

    public List<SelectItem> getCategoryItems() {
        if (categoryItems == null){
            categoryItems = new ArrayList<SelectItem>();
            for (Category category : getCategories()) {
                categoryItems.add(new SelectItem(category, category.getName()));
            }
        }
        return categoryItems;
    }
}
