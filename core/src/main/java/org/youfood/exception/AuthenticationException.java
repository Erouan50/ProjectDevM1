package org.youfood.exception;

import javax.ejb.ApplicationException;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
@ApplicationException
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String username, String password) {
        super("Bad credentials " + username + "/" + password);
    }
}
