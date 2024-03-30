package Model;

import java.util.Date;

public class PremiumModel extends ListenerModel{
    private int remainingSubscriptionDay;
    public PremiumModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(username, password, fullName, email, phoneNumber, birthDate);
    }

    public int getRemainingSubscriptionDay() {
        return remainingSubscriptionDay;
    }

    public void setRemainingSubscriptionDay(int remainingSubscriptionDay) {
        this.remainingSubscriptionDay = remainingSubscriptionDay;
    }
}
