package api;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserRegistrationData {

    private String userName;
    private String login;
    private String phoneNumber;
    private String password;
    private String passwordValidation;

    public UserRegistrationData(String name, String email, String phoneNumber, String password, String passwordValidation) {
        this.userName = name;
        this.login = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.passwordValidation = passwordValidation;
    }

}