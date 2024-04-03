package model;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends UserAccount{
    private static Admin admin;

    private Admin(String name,String password,String fullName,String email,String phoneNumber,Date birthDate){
        super(name,password,fullName,email,phoneNumber,birthDate);
    }

    public static Admin getAdmin() {
        if (admin == null) {
            Admin.admin = new Admin("Masih", "Masih1383", "MasihRoughani", "im.the.best.no.one.can.pass@gmail.com", "09135111562", new Date(2005, 1, 8));
            ArrayList<UserAccount> backUp = Database.getData().getAllUsers();
            backUp.add(admin);
            Database.getData().setAllUsers(backUp);
            return admin;
        }
        return admin;
    }
}
