package todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import todo.entities.ErrorMessage;

@ControllerAdvice
@ResponseStatus
public class TodoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<ErrorMessage> todoNotFoundException(TodoNotFoundException exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body((errorMessage));
    }
}
