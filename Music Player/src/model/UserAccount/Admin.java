package model.UserAccount;

import java.time.LocalDate;

public class Admin extends UserAccount
{
    private static Admin admin;
    private Admin(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord)
    {
        super(email,name,phoneNumber,dateOfBirth,userName,passWord);
    }
    public static synchronized Admin getAdmin(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord)
    {
        if (admin == null)
        {
            admin = new Admin(email,name,phoneNumber,dateOfBirth,userName,passWord);
        }
        return admin;
    }
}
