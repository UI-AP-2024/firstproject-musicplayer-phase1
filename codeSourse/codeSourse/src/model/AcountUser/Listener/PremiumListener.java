package model.AcountUser.Listener;
import java.util.Date;

public class PremiumListener extends Listener {
    private int remainingSubDays;

    //*********************************************

    public PremiumListener(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate, int remainingSubscriptionDays) {
        super(userName, password, fullName, email, phoneNumber, birthDate);
        this.remainingSubDays = remainingSubscriptionDays;
    }
    //*********************************************
    public int getRemainingSubDays() {
        return remainingSubDays;
    }
    public void setRemainingSubDays(int remainingSubDays) {
        this.remainingSubDays = remainingSubDays;
    }
    //*********************************************
    @Override
    public String toString() {
        return "PremiumListener{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", remainingSubscriptionDays=" + remainingSubDays +
                '}';
    }
}

