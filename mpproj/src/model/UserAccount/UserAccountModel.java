package model.UserAccount;

import java.time.LocalDate;
import java.util.Date;

public abstract class UserAccountModel {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate birthday;


    public UserAccountModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "username = " + username +
                ", name = " + name +
                ", email = " + email +
                ", phoneNumber = " + phoneNumber +
                ", birthday = " + birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}

