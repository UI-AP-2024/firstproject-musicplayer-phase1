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

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password = password;
    }
    public String getFullName(){
        return this.fullName;
    }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public Date getBirthday(){
        return this.birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    public int[] getBirthDate(){
        return this.birthDate;
    }

    @Override
    public String toString(){
        return "\nUsername : " + username +
                "\nName : "+ fullName;
    }
}
