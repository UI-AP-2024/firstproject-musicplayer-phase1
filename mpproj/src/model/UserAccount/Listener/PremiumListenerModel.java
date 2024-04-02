package model.UserAccount.Listener;

import java.util.Date;

public class PremiumListenerModel extends ListenerModel{
    private int remainingSubscriptionDays;

    public PremiumListenerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double accountCredit) {
        super(username, password, name, email, phoneNumber, birthday, accountCredit);
    }
}
