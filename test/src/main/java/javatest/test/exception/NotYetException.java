package javatest.test.exception;

public class NotYetException extends RuntimeException{
    public NotYetException() {
    }

    public NotYetException(String message) {
        super(message);
    }
}
