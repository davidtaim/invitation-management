package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;

public class InvitationGuestRequestDTO {

    @NotBlank(message = "Guest Id is required")
    String guestId;

    public String getGuestsId() {
        return guestId;
    }

    public void setGuestId(String guestId) {
        this.guestId = guestId;
    }

}
