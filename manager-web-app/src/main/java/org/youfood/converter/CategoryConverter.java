package org.youfood.converter;

import org.youfood.model.Category;
import org.youfood.services.CategoryService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class CategoryConverter implements Converter {

    @EJB
    private CategoryService categoryService;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s.isEmpty()) {
            return null;
        }
        Long id = Long.parseLong(s);
        return categoryService.getCategoryById(id);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Category) {
            return ((Category)o).getId().toString();
        } else {
            return "";
        }
    }
}
