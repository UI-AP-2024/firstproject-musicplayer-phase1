package Models.User;

import java.util.Date;

public class PremiumListener extends Listener {
    private int remainingDays;

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }

    public PremiumListener(String username, String password, String name, String email, String phoneNumber, Date dateOfBirth, double accountCredit, Date subscriptionEndDate, int remainingDays) {
        super(username, password, name, email, phoneNumber, dateOfBirth, accountCredit, subscriptionEndDate);
        this.remainingDays = remainingDays;
    }
}
