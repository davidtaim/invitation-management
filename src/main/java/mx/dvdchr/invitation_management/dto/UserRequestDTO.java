package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserEmailValidationGroup;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserPasswordValidationGroup;
import mx.dvdchr.invitation_management.dto.validator.UpdateUserValidationGroup;

public class UserRequestDTO {

    @NotBlank(message = "Name is required", groups = { Default.class, UpdateUserValidationGroup.class })
    private String name;

    @NotBlank(message = "Middle name is required", groups = { Default.class, UpdateUserValidationGroup.class })
    private String middleName;

    @NotBlank(message = "Last name is required", groups = { Default.class, UpdateUserValidationGroup.class })
    private String lastName;

    @NotBlank(message = "Email is required", groups = { Default.class, UpdateUserEmailValidationGroup.class })
    @Email(message = "Enter a string with a valid email format")
    private String email;

    @NotBlank(message = "Password is required", groups = { Default.class, UpdateUserPasswordValidationGroup.class })
    @Size(min = 8, message = "Password must have at least 8 characteres")
    private String password;

    @NotBlank(message = "Role is required", groups = { Default.class, UpdateUserValidationGroup.class })
    private String roleId;

    @NotNull(message = "Active is required", groups = UpdateUserValidationGroup.class)
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
