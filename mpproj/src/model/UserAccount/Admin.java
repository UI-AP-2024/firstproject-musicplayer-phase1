package model.UserAccount;

import java.util.Date;

public class Admin extends UserAccount{
    private static Admin admin;

    public Admin(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        super(username, password, name, email, phoneNumber, birthday);
    }

    public static Admin getAdmin(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        if (admin == null)
            admin = new Admin(username, password, name, email, phoneNumber, birthday);
        return admin;
    }
}
