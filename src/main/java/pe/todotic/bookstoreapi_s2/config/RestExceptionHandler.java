package pe.todotic.bookstoreapi_s2.config;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pe.todotic.bookstoreapi_s2.exception.MediaFileNotFoundException;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class RestExceptionHandler {

    @Autowired
    private Messag@eSource messageSource;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ProblemDetail handleValidationError (MethodArgumentNotValidException manve){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Unprocessable Entity");
        problemDetail.setType(URI.create("https://api.sbashop.com/errors/unprocessable-entity"));
        problemDetail.setDetail("The entity can't proccesed because it has errors");

        List<FieldError> fieldErrors=manve.getFieldErrors();
        List<String> errors=new ArrayList<>();

        for (FieldError fe: fieldErrors){
            String message=messageSource.getMessage(fe, Locale.getDefault());
            errors.add(message);
        }
        problemDetail.setProperty("errors", errors);
        return problemDetail ;
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class/*,MediaFileNotFoundException.class*/})
    void handleEntityNotFoundException(){

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    ProblemDetail handleDataIntegrityViolationException(){
        ProblemDetail problemDetail=ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("BAD_REQUEST");
        problemDetail.setType(URI.create("https://api.sbashop.com/errors/bad-request"));
        problemDetail.setDetail("El email ingresado ya se encuentra registrado");
        return  problemDetail;
    }

    //HOLA MUNDOOOOOOOOOO
}
