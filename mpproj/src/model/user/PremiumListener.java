package model.user;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PremiumListener extends Listener{
    private int remainingDaysOfPremium;

    public PremiumListener(String password,String username, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit, int remainingDaysOfPremium,Date premiumExpirationDate ) {
        super(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, accountCredit);
        this.remainingDaysOfPremium = remainingDaysOfPremium;
        super.setPremiumExpirationDate(premiumExpirationDate);
        shortenRemainingDays();
    }

    public int getRemainingDaysOfPremium() {
        return remainingDaysOfPremium;
    }

    public void setRemainingDaysOfPremium(int remainingDaysOfPremium) {
        this.remainingDaysOfPremium = remainingDaysOfPremium;
    }
    private void shortenRemainingDays(){
        Timer timer = new Timer();
		
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				remainingDaysOfPremium--;
			}		
		};

        timer.scheduleAtFixedRate(task, 0, 24*60*60000);
        //every one day after calling the method
    }
    
    
}
