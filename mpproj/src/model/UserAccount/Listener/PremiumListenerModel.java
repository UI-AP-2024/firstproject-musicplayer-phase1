package model.UserAccount.Listener;

import java.util.Date;

public class PremiumListenerModel extends ListenerModel{
    private int remainingSubscriptionDays;

    public PremiumListenerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double accountCredit) {
        super(username, password, name, email, phoneNumber, birthday, accountCredit);
    }

    @Override
    public String toString() {
        return super.toString() +
                ", remainingSubscriptionDays=" + remainingSubscriptionDays;
    }

    public int getRemainingSubscriptionDays() {
        return remainingSubscriptionDays;
    }

    public void setRemainingSubscriptionDays(int remainingSubscriptionDays) {
        this.remainingSubscriptionDays = remainingSubscriptionDays;
    }
}
