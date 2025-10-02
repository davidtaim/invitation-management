package mx.dvdchr.invitation_management.exception;

public class SeatCategoryNameAlreadyExistsException extends RuntimeException {
    public SeatCategoryNameAlreadyExistsException(String message) {
        super(message);
    }
}
