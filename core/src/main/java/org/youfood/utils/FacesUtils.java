package org.youfood.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ResourceBundle;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class FacesUtils {

    public static ResourceBundle getResourceBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
    }

    public static void addErrorMessage(String summary, String details) {
        addErrorMessage(null, summary, details);
    }

    public static void addErrorMessage(String id, String summary, String details) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,summary, details);
        context.addMessage(id, message);
    }
}
