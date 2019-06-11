package edu.uni.auth.exception;

/**
 * @author 何亮
 */
public class RoleException extends Exception {
    public RoleException() {
    }

    public RoleException(String message) {
        super(message);
    }

    public RoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RoleException(Throwable cause) {
        super(cause);
    }
}
