package group.fortil.model;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name="user")
public class UserModel {

    @Id
    @UuidGenerator
    @Column(name="u_index")
    private UUID userIndex;
    @Column(name="last_name", length=50, nullable=false)
    private String lastName;

    @Column(name="first_name", length=50, nullable=false)
    private String firstName;

    @Column(name="password", length=50, nullable=false)
    private String password;

    @Column(name="email", length=50, nullable=false, unique = true)
    private String email;

    public UserModel() {
    }

    public UserModel(UUID userIndex, String password, String email) {
        this.userIndex = userIndex;
        this.password = password;
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return userIndex;
    }

    @Override
    public String toString() {
        return "UserModel{" +
            "userIndex=" + userIndex +
            ", lastName='" + lastName + '\'' +
            ", firstName='" + firstName + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
