package model.UserAccount.Listener;

import java.time.LocalDate;
import java.util.Date;

public class PremiumListenerModel extends ListenerModel{
    private int remainingSubscriptionDays;

    public PremiumListenerModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday, LocalDate date) {
        super(username, password, name, email, phoneNumber, birthday);
        this.setSubscriptionExpirationDate(date);
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
