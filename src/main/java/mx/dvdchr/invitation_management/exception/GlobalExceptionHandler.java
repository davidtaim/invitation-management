package mx.dvdchr.invitation_management.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<Map<String, String>> handleHandlerMethodValidationException(
            HandlerMethodValidationException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getAllErrors().forEach(error -> errors.put("message", error.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UuidInvalidException.class)
    public ResponseEntity<Map<String, String>> handleUUIDInvalidException(UuidInvalidException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(RoleNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(GuestNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleGuestNotFoundException(GuestNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEventNotFoundException(EventNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(EventSeatNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEventSeatNotFoundException(EventSeatNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(InvitationNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleInvitationNotFoundException(InvitationNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(SeatCategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSeatCategoryNotFoundException(SeatCategoryNotFoundException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();

        log.warn(ex.getMessage());

        errors.put("message", "Email address already exists");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RoleNameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleRoleNameAlreadyExistsException(RoleNameAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();

        log.warn(ex.getMessage());

        errors.put("message", "Role with the same name already exists");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(SeatCategoryNameAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleSeatCategoryNameAlreadyExistsException(
            SeatCategoryNameAlreadyExistsException ex) {
        Map<String, String> errors = new HashMap<>();

        log.warn(ex.getMessage());

        errors.put("message", "Seat category with the same name already exists");

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put(ex.getName(), ex.getMostSpecificCause().getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotValidEnumStringException.class)
    public ResponseEntity<Map<String, String>> handleNotValidEnumStringException(NotValidEnumStringException ex) {
        Map<String, String> errors = new HashMap<>();

        errors.put("message", ex.getMessage());

        return ResponseEntity.badRequest().body(errors);
    }

}
