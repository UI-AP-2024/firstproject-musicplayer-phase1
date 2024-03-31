package model;

import java.util.Date;

public class Admin extends UserAccount {
    private static Admin admin;

    public static Admin getAdmin() {
        if (admin == null)
            admin = new Admin();
        return admin;
    }

    public Admin(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
    }
}
