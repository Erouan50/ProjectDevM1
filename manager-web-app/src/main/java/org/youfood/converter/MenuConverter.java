package org.youfood.converter;

import org.youfood.model.Menu;
import org.youfood.services.MenuService;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
public class MenuConverter implements Converter{

    @EJB
    private MenuService menuService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        }
        Long id = Long.parseLong(value);
        return menuService.getMenuById(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Menu) {
            return String.valueOf(((Menu)value).getId());
        } else {
            return "";
        }
    }
}
