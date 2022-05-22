package ru.rsreu.restaurantAutomation.datalayer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.Objects;

/**
 * Represents user
 */
public class User {

    /**
     * user id
     */
    @Getter
    @Setter
    private int id;
    /**
     * user name
     */
    @Getter
    @Setter
    private String name;
    /**
     * user role
     */
    @Getter
    @Setter
    private Role role;
    /**
     * user phone number
     */
    @Getter
    @Setter
    private String phoneNumber;
    /**
     * user email
     */
    @Getter
    @Setter
    private String email;
    /**
     * user lock status
     */
    @Getter
    @Setter
    private LockStatus lockStatus;
    /**
     * user password
     */
    @Getter
    @Setter
    private String password;
    /**
     * user authorization
     */
    @Getter
    @Setter
    private Authorization authorization;

    public User(int id, String name, Role role, String phoneNumber, String email,
                LockStatus lockStatus, String password, Authorization authorization) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.lockStatus = lockStatus;
        this.password = password;
        this.authorization = authorization;
    }
    /**
     * not found user
     */
    public static final User NULL_USER = new User(0, "", Role.NOT_FOUND,
            "", "", LockStatus.NOT_FOUND, "", Authorization.NOT_FOUND);


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && name.equals(user.name) && role == user.role && phoneNumber.equals(user.phoneNumber) && email.equals(user.email) && lockStatus == user.lockStatus && password.equals(user.password) && authorization == user.authorization;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, phoneNumber, email, lockStatus, password, authorization);
    }
}
