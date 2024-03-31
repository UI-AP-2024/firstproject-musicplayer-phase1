package model;

import java.util.Date;

public class Admin extends UserAccount {
    private static Admin admin;

    public static Admin getAdmin(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        if (admin == null)
            admin = new Admin(userName,password,name,email,phoneNumber,dateOfBirth);
        return admin;
    }

    public static Admin getAdmin(){
        return admin;
    }

    private Admin(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
    }
}
