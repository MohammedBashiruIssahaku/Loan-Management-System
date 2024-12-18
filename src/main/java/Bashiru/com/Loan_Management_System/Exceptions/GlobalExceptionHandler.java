package Bashiru.com.Loan_Management_System.Exceptions;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExecutionControl.UserException.class)
    public ResponseEntity<ErrorDetails> myExceptionHandler(ExecutionControl.UserException pe , WebRequest req)
    {
        ErrorDetails err  = new ErrorDetails();
        err.setDescription(req.getDescription(false));
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(pe.getMessage());

        return new ResponseEntity<ErrorDetails>(err, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> myExceptionHandler(MethodArgumentNotValidException pe)
    {
        ErrorDetails err  = new ErrorDetails();
        err.setDescription("BAD_REQUEST");
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(pe.getFieldError().getDefaultMessage());

        return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetails> myExceptionHandler(NoSuchElementException pe)
    {
        ErrorDetails err  = new ErrorDetails();
        err.setDescription("BAD_REQUEST");
        err.setTimestamp(LocalDateTime.now());
        err.setMessage("You don't have any request to process");

        return new ResponseEntity<ErrorDetails>(err,HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> exceptionHandler(Exception e)
    {
        ErrorDetails err  = new ErrorDetails();
        err.setDescription("getting Error");
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(e.getMessage());

        return new ResponseEntity<ErrorDetails>(err,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
