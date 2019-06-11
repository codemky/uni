package edu.uni.auth.exception;

/**
 * @author 何亮
 */
public class UnivInfoSUPException extends Exception {
    public UnivInfoSUPException() {
    }

    public UnivInfoSUPException(String message) {
        super(message);
    }

    public UnivInfoSUPException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnivInfoSUPException(Throwable cause) {
        super(cause);
    }
}
