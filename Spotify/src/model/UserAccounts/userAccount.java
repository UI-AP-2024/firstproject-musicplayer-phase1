package model.UserAccounts;

import java.util.Date;

public abstract class userAccount {
    private int userId;
    private static int userIdCounter= 0;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Date birthday;

    public userAccount(String password, String fullName, String email, String phoneNumber, Date birthday) {
        this.userId = ++userIdCounter;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        StringBuilder context=new StringBuilder();
        context.append("user id : ");
        context.append(userId);
        context.append("\nuser name : ");
        context.append(fullName);
        context.append("\nuser email : ");
        context.append(email);
        context.append("\nuser password : ");
        context.append(password);
        context.append("\nphone number : ");
        context.append(phoneNumber);
        context.append("\ndate of birthday : ");
        context.append(birthday.getYear()+1900);
        context.append("/");
        context.append(birthday.getMonth()+1);
        context.append("/");
        context.append(birthday.getDate());
        return context.toString();
    }
    public int getUserId() {
        return userId;
    }


    public static int getUserIdCounter() {
        return userIdCounter;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
