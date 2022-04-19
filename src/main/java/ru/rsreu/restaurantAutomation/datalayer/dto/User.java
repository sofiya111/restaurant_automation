package ru.rsreu.restaurantAutomation.datalayer.dto;

import java.util.Objects;

/**
 * Represents user
 */
public class User {

    /**
     * user id
     */
    private int id;
    /**
     * user name
     */
    private String name;
    /**
     * user role
     */
    private Role role;
    /**
     * user phone number
     */
    private String phoneNumber;
    /**
     * user email
     */
    private String email;
    /**
     * user lock status
     */
    private LockStatus lockStatus;
    /**
     * user password
     */
    private String password;
    /**
     * user authorization
     */
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LockStatus isLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(LockStatus lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authorization getAuthorization() {
        return authorization;
    }

    public void setAuthorization(Authorization authorization) {
        this.authorization = authorization;
    }

    /**
     * not found user
     */
    public static final User NULL_USER = new User(0, "", Role.NOT_FOUND,
            "", "", LockStatus.NOT_FOUND, "", Authorization.NOT_FOUND);

    public String getPassword() {
        return password;
    }

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
