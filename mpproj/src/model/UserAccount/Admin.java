package model.UserAccount;

public class Admin {
    private static Admin admin;
    public static Admin getAdmin() {
        if (admin == null)
            admin = new Admin();
        return admin;
    }
}
