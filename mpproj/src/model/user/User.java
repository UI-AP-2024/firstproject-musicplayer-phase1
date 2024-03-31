package model.user;

import java.util.Date;

public abstract class User {
    private final String id;
    private String password;
    private String firstName;//type?
    private String lastName;//?
    private String emailAddress;//?
    private String phoneNumber;
    private Date birthDate;
    private static long counter =0;
    public User(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate) {
        counter++;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.id=String.valueOf(counter)+"-"+firstName+"-"+lastName;   
    }
    public String getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public Date getBirthDate() {
        return birthDate;
    }
    public static long getCounter() {
        return counter;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
