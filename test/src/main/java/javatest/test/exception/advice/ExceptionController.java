package javatest.test.exception.advice;

import javatest.test.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AlreadyFullException.class)
    public ErrorResult handleAlreadyFullException(AlreadyFullException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DuplicateTimeException.class)
    public ErrorResult handlerDuplicateTimeException(DuplicateTimeException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SameLectureException.class)
    public ErrorResult handlerSameLectureException(SameLectureException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OtherDepartException.class)
    public ErrorResult handlerOtherDepartException(OtherDepartException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OverCreditException.class)
    public ErrorResult handlerOverCreditException(OverCreditException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotEnrolSubDepartmentException.class)
    public ErrorResult handlerNotEnrolSubDepartmentException(NotEnrolSubDepartmentException e){
        return new ErrorResult(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotYetException.class)
    public ErrorResult handlerNotYetException(NotYetException e){
        return new ErrorResult(e.getMessage());
    }
}
