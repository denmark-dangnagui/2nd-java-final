package javatest.test.exception;

public class OtherDepartException extends RuntimeException{
    public OtherDepartException() {
    }

    public OtherDepartException(String message) {
        super(message);
    }
}
