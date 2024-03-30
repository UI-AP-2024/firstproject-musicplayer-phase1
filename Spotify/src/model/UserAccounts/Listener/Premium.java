package model.UserAccounts.Listener;

import model.UserAccounts.Listener.Listener;

import java.util.Date;

public class Premium extends Listener {
    public Premium(String userId, String password, String fullName, String email, String phoneNumber, Date birthday) {
        super(userId, password, fullName, email, phoneNumber, birthday);
    }
}
