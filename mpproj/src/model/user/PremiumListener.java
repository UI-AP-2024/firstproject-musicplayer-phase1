package model.user;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PremiumListener extends Listener{
    private int remainingDaysOfPremium;

    public PremiumListener(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit, int remainingDaysOfPremium,Date premiumExpirationDate ) {
        super(password,username, name, emailAddress, phoneNumber, birthDate, accountCredit);
        this.remainingDaysOfPremium = remainingDaysOfPremium;
        super.setPremiumExpirationDate(premiumExpirationDate);
    }

    public int getRemainingDaysOfPremium() {
        return remainingDaysOfPremium;
    }

    public void setRemainingDaysOfPremium(int remainingDaysOfPremium) {
        this.remainingDaysOfPremium = remainingDaysOfPremium;
    }

}
