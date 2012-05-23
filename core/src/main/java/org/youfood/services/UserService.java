package org.youfood.services;

import org.youfood.exception.AuthenticationException;
import org.youfood.exception.UnknownUserException;
import org.youfood.model.Manager;
import org.youfood.model.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@Stateless
public class UserService {

    @PersistenceContext(unitName = "Youfood-PU")
    private EntityManager em;

    public Manager authenticateManager(String username, String password) {
        try{
            Manager user = getManagerByUsername(username);
            if (user.getPassword().equals(password)) {
                return user;
            }
        } catch (UnknownUserException e) {}
        throw new AuthenticationException(username, password);
    }

    public Manager getManagerByUsername(String username) {
        Query query = em.createNamedQuery("findManagerByUsername");
        query.setParameter("username", username);
        try {
            return  (Manager) query.getSingleResult();
        } catch (NoResultException e) {
            throw new UnknownUserException(username);
        }
    }
}
