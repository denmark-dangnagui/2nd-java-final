package javatest.test.exception;

public class AlreadyFullException extends RuntimeException{

    public AlreadyFullException(){

    }

    public AlreadyFullException(String message){
        super(message);
    }
}
