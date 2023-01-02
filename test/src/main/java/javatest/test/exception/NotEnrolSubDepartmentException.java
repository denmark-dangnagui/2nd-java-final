package javatest.test.exception;

public class NotEnrolSubDepartmentException extends RuntimeException {
    public NotEnrolSubDepartmentException() {
    }

    public NotEnrolSubDepartmentException(String message) {
        super(message);
    }
}
