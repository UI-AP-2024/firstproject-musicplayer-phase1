package model;

import java.time.LocalDate;
import java.util.Date;

public abstract class UserAccount
{
    private String userName;
    private String passWord;
    private String name;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    public UserAccount(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord)
    {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.userName = userName;
        this.passWord = passWord;
    }
    public String getUserName()
    {
        return userName;
    }
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    public String getPassWord()
    {
        return passWord;
    }
    public void setPassWord(String passWord)
    {
        this.passWord = passWord;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public LocalDate getDateOfBirth()
    {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
}
