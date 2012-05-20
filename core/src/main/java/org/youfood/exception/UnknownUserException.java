package org.youfood.exception;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class UnknownUserException extends RuntimeException {
    private String username;

    public UnknownUserException(String username) {
        super("Unknow user with username: " + username);
        this.username = username;
    }

    public UnknownUserException(String username, Exception cause) {
        super("Unknow user with username: " + username);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
