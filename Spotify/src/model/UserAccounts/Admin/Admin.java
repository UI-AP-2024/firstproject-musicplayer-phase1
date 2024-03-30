package model.UserAccounts.Admin;

import model.UserAccounts.userAccount;

import java.util.Date;

public class Admin extends userAccount {
    private Admin admin;
    private Admin(String password, String fullName, String email, String phoneNumber, Date birthday) {
        super(password, fullName, email, phoneNumber, birthday);
    }

    public Admin getAdmin(String password, String fullName, String email, String phoneNumber, Date birthday){
        if (admin==null){
            admin = new Admin(password, fullName, email, phoneNumber, birthday);
        }
        return admin;
    }
    public Admin getAdmin(){
        return admin;
    }
}
