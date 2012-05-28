package org.youfood.resources.impl;

import org.youfood.model.Category;
import org.youfood.resources.CategoryResource;
import org.youfood.services.CategoryService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.PathParam;
import java.util.List;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class DefaultCategoryResource implements CategoryResource{

    @EJB
    private CategoryService categoryService;

    @Override
    public Category getCategoryById(@PathParam("id") Long id) {
        return categoryService.getCategoryById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
