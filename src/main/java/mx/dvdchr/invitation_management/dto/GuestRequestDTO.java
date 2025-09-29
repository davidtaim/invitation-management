package mx.dvdchr.invitation_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mx.dvdchr.invitation_management.dto.validator.UpdateGuestValidationGroup;

public class GuestRequestDTO {
    
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Email must be not null")
    @Email(message = "Enter a string with a valid email format")
    private String email;

    @NotNull(message = "Phone must be not null")
    private String phone;

    @NotNull(message = "Company must be not null")
    private String company;

    @NotNull(message = "Active must be not null", groups = UpdateGuestValidationGroup.class)
    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
}
