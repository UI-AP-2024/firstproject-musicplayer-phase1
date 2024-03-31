package model.UserAccounts.Admin;

import model.UserAccounts.userAccount;

import java.util.Date;

public class Admin extends userAccount {
    private static Admin admin;
    private Admin(String userId,String password, String fullName, String email, String phoneNumber, Date birthday) {
        super(userId,password, fullName, email, phoneNumber, birthday);
    }

    public static Admin getAdmin(String userId,String password, String fullName, String email, String phoneNumber, Date birthday){
        if (admin==null){
            admin = new Admin(userId,password, fullName, email, phoneNumber, birthday);
        }
        return admin;
    }
    public static Admin getAdmin(){
        return admin;
    }
}
