package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.dto.validator.UpdateRoleValidationGroup;

public class RoleRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Active status is requires", groups = UpdateRoleValidationGroup.class)
    private boolean isActive;

    @NotBlank(message = "Created at is required", groups = UpdateRoleValidationGroup.class)
    private String createdAt;

    @NotBlank(message = "Updated at is required", groups = UpdateRoleValidationGroup.class)
    private String updatedAt;

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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
