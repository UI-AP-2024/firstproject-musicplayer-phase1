package model.UserAccount;

import model.SubscriptionPlan;

import java.util.Date;

public class Admin extends User {
    private static Admin admin;

    private Admin(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
    }

    public static Admin getAdmin(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription) {
        if (admin == null) {
            admin = new Admin(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        }
            return admin;
    }
}
