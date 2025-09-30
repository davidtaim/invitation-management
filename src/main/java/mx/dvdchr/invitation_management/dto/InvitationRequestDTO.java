package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;
import mx.dvdchr.invitation_management.dto.validator.UpdateInvitationValidationGroup;

public class InvitationRequestDTO {

    // TODO List<InvitationGuest> guests;

    @NotBlank(message = "Event is required", groups = UpdateInvitationValidationGroup.class)
    private String eventId;

    private String uniqueCode;

    @NotBlank(message = "Status is required", groups = UpdateInvitationValidationGroup.class)
    private String status;

    private String sentAt;

    private String respondedAt;

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSentAt() {
        return sentAt;
    }

    public void setSentAt(String sentAt) {
        this.sentAt = sentAt;
    }

    public String getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(String respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

}
