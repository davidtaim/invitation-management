package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.dto.validator.UpdateRoleValidationGroup;

public class RoleRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Active is required", groups = UpdateRoleValidationGroup.class)
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
