package model.UserAccount;

import java.util.Date;

public class AdminModel extends UserAccountModel{
    private static AdminModel admin;

    private AdminModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        super(username, password, name, email, phoneNumber, birthday);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public static AdminModel getAdmin(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        if (admin == null)
            admin = new AdminModel(username, password, name, email, phoneNumber, birthday);
        return admin;
    }
}
