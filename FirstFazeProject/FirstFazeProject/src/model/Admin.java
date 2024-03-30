package model;

public class Admin extends UserAccount{
    private static Admin admin;

    private Admin() {}

    public static Admin getAdmin() {
        return admin;
    }
}
