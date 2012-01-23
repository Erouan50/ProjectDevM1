package org.youfood.exception;

/**
 * @author Antoine ROUAZE <antoine.rouaze AT zenika.com>
 */
public class CoreException extends RuntimeException {

    public CoreException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
