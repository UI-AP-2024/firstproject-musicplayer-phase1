package model.UserAccount;

import java.time.LocalDate;
import java.util.Date;

public class AdminModel extends UserAccountModel{
    private static AdminModel admin;

    private AdminModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday) {
        super(username, password, name, email, phoneNumber, birthday);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static AdminModel getAdmin(String username, String password, String name, String email, String phoneNumber, LocalDate birthday) {
        if (admin == null)
            admin = new AdminModel(username, password, name, email, phoneNumber, birthday);
        return admin;
    }
}
