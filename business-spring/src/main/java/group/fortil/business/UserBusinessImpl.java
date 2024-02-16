package group.fortil.business;

public class UserBusinessImpl extends BusinessImpl implements IUserBusiness {

    private String firstName;

    private String lastName;

    private String emailAddress;

    private String loginCode;

    public UserBusinessImpl() {
        super();
    }

    public UserBusinessImpl(
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

    public UserBusinessImpl(
        Long index,
        String firstName,
        String lastName,
        String emailAddress,
        String loginCode
    ) {
        super(index);
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.loginCode = loginCode;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return this.emailAddress;
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
        return "UserBusinessImpl{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", loginCode='***\'' " +
            '}';
    }
}
