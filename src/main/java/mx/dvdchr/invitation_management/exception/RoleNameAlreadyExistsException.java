package mx.dvdchr.invitation_management.exception;

public class RoleNameAlreadyExistsException extends RuntimeException {
    public RoleNameAlreadyExistsException(String message) {
        super(message);
    }
}
