package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import mx.dvdchr.invitation_management.dto.validator.UpdateEventValidationGroup;

public class EventRequestDTO {

    @NotBlank(message = "Organizer is required")
    private String organizerId;

    @NotBlank(message = "Title is required")
    @Size(min = 5, message = "Title requires a minimum size of 5")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Start date is required")
    private String startDatetime;

    @NotBlank(message = "End date is required")
    private String endDatetime;

    @NotBlank(message = "Location is required")
    private String location;

    @NotNull(message = "Status is required", groups = UpdateEventValidationGroup.class)
    private String status;

    public String getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(String organizerId) {
        this.organizerId = organizerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(String startDatetime) {
        this.startDatetime = startDatetime;
    }

    public String getEndDatetime() {
        return endDatetime;
    }

    public void setEndDatetime(String endDatetime) {
        this.endDatetime = endDatetime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
