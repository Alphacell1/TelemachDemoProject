package hr.tomislav.planinic.telemach.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * A global exception handler that catches specific exceptions and returns
 * meaningful HTTP status codes and messages.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Catches ResourceNotFoundException and returns a 404 response.
     *
     * @param ex the exception thrown
     * @return a ResponseEntity with HTTP status 404 and exception message
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
}
