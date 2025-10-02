package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;

public class InvitationGuestStatusRequestDTO {

    @NotBlank(message = "Status is required")
    private String status;

    public String getStatus() {
        return status;
    }

}
