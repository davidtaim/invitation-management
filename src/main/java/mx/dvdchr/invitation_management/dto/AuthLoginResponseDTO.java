package mx.dvdchr.invitation_management.dto;

public class AuthLoginResponseDTO {

    private String accessToken;

    public AuthLoginResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
