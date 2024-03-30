package model;

import java.util.Date;

public class Admin extends UserAccount{
    private static Admin admin;

    private Admin(String name,String password,String fullName,String email,String phoneNumber,Date birthDate){
        super(name,password,fullName,email,phoneNumber,birthDate);
    }

    public static Admin getAdmin() {
        return admin;
    }

    public static String setAdmin(String name,String password,String fullName,String email,String phoneNumber,Date birthDate) {
        if (admin == null) {
            Admin.admin = new Admin(name, password, fullName, email, phoneNumber, birthDate);
            return "Admin was made successfully";
        }
        return "An admin was made before";
    }
}
