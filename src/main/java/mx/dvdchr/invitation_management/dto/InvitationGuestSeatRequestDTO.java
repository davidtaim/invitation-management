package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;

public class InvitationGuestSeatRequestDTO {

    @NotBlank(message = "Seat is required")
    private String seatId;

    public String getSeatId() {
        return seatId;
    }

}
