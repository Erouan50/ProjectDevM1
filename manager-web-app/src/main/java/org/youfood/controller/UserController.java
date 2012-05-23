package org.youfood.controller;

import org.youfood.exception.AuthenticationException;
import org.youfood.model.Manager;
import org.youfood.model.User;
import org.youfood.services.UserService;
import org.youfood.utils.FacesUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable{

    @EJB
    private UserService userService;

    private String username;
    private String password;

    private Manager user;

    public String login() {
        try {
            user = userService.authenticateManager(username, password);
            return "/auth/manager_home?faces-redirect=true";
        } catch (AuthenticationException e) {
            ResourceBundle bundle = FacesUtils.getResourceBundle();
            FacesUtils.addErrorMessage("form", bundle.getString("views.login.error.authentication"), null);
            Logger.getLogger(this.getClass().getName()).log(Level.WARNING, "Authentication error", e);
        }
        return null;
    }

    public String logout() {
        user = null;
        return "/login?faces-redirect=true";
    }

    public boolean isAuthenticated() {
        return user != null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }
}
