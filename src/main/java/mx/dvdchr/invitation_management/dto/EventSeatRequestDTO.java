package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.dto.validator.UpdateEventSeatValidationGroup;

public class EventSeatRequestDTO {

    @NotNull(message = "Seat Number is required", groups = UpdateEventSeatValidationGroup.class)
    private String seatNumber;

    @NotNull(message = "Table Number is required", groups = UpdateEventSeatValidationGroup.class)
    private String tableNumber;

    @NotNull(message = "Seat Category is required", groups = UpdateEventSeatValidationGroup.class)
    private String seatCategoryId;

    @NotNull(message = "Available is required", groups = UpdateEventSeatValidationGroup.class)
    private Boolean isAvailable;

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getSeatCategoryId() {
        return seatCategoryId;
    }

    public void setSeatCategoryId(String seatCategoryId) {
        this.seatCategoryId = seatCategoryId;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

}
