package model.UserAccount.Listener;

import java.util.Date;

public class PremiumListener extends Listener{
    private int remainingDaysOfSubscription;

    public PremiumListener(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, int accountCredit) {
        super(username, password, name, email, phoneNumber, birthday, accountCredit);
    }
}
