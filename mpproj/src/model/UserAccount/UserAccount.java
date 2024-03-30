package model.UserAccount;

import model.DataBase.DataBase;

import java.util.Date;

public abstract class UserAccount {
    private String username;
    private String password;
    private StringBuilder name;
    private String email;
    private String phoneNumber;
    private final Date birthday;

    public UserAccount(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = new Date();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public StringBuilder getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(StringBuilder name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
