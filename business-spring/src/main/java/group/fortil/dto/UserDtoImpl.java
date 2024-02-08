package group.fortil.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDtoImpl extends DtoImpl<Long> implements IUserDto {

    @NotEmpty(message = "User should has a first name")
    @Size(min = 3, max = 50, message = "User's first name must contains at least 3 character")
    private String firstName;

    @Size(min = 3, max = 50, message = "User's first name must contains at least 3 character")
    private String lastName;

    @Email
    @NotEmpty(message = "User should fill a valid Email address")
    private String emailAddress;

    @NotBlank
    @NotEmpty(message = "User's password could not be null")
    @Size(min = 3, max = Integer.MAX_VALUE, message = "User's first name must contains at least 3 character")
    private String loginCode;

    public UserDtoImpl() {
        super();
    }

    public UserDtoImpl(
        String firstName,
        String lastName,
        String emailAddress,
        String loginCode
    ) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.loginCode = loginCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    @Override
    public String toString() {
        return "UserDtoImpl{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", loginCode='***'" +
            '}';
    }
}
