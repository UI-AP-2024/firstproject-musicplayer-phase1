package Model;

import java.time.LocalDate;

public abstract class UserAccount {
    private String userName;
    private String password;
    private String firstAndLastName;
    private String email;
    private String phoneNumber;
    private LocalDate birthDate;

    UserAccount(String userName, String password, String firstAndLastName,
                String email, String phoneNumber, int year, int month, int day) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.firstAndLastName = firstAndLastName;
        this.userName = userName;
        this.birthDate = LocalDate.of(year, month, day);
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public String getPassword() {
        return password;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstAndLastName(String firstAndLastName) {
        this.firstAndLastName = firstAndLastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "UserName: "+getUserName()+"\nFirst And LastName: "+
                firstAndLastName+"\nEmail: "+email+"\nPhoneNumber: "+phoneNumber+"\nBirthDate: "+
                birthDate;
    }
}
