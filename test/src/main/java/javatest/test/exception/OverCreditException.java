package javatest.test.exception;

public class OverCreditException extends RuntimeException{

    public OverCreditException(){

    }

    public OverCreditException(String message){
        super(message);
    }
}
