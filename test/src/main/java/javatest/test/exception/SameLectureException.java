package javatest.test.exception;

public class SameLectureException extends RuntimeException {

    public SameLectureException() {
    }

    public SameLectureException(String message) {
        super(message);
    }
}
