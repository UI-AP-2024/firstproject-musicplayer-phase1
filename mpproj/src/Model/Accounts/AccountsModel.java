package Model.Accounts;

import java.util.Date;

abstract public class AccountsModel {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Date birthday;

    private int[] birthDate;

    public AccountsModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = new Date((birthDate[0] - 1900) , (birthDate[1] - 1) , birthDate[2]);
        this.birthDate = birthDate;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public String getFullName(){
        return this.fullName;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public Date getBirthday(){
        return this.birthday;
    }

    public int[] getBirthDate(){
        return this.birthDate;
    }

    @Override
    public String toString(){
        return "Username : " + username ;
    }
}
