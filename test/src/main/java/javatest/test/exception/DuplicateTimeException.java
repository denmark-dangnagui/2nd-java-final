package javatest.test.exception;

public class DuplicateTimeException extends RuntimeException{

    public DuplicateTimeException(){

    }

    public DuplicateTimeException(String message){
        super(message);
    }
}
