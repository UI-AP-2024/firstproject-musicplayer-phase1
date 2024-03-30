package model.UserAccount;

import java.time.LocalDate;

public class Admin extends UserAccount
{
    private static Admin admin;
    private Admin(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth)
    {
        super(userName,passWord,name,email,phoneNumber,dateOfBirth);
    }
    public static synchronized Admin getAdmin(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth)
    {
        if (admin == null)
        {
            admin = new Admin(userName,passWord,name,email,phoneNumber,dateOfBirth);
        }
        return admin;
    }
    @Override
    public String toString()
    {
        return "UserName: " + getUserName()+"\t"+"PassWord: "+getPassWord()+"\t"+"Name: "+getName()+"\t"+"Email: "+getEmail()+"\t"+"PhoneNumber: "+getPhoneNumber()+"\t"+"Birth Date: "+getDateOfBirth();
    }
}
