package model.AccountUser.Listener.TypeOfListener;
import model.AccountUser.Listener.Listener;

import java.util.Date;

public class PremiumListener extends Listener {
    private int remainingSubDays;

    //*********************************************

    public PremiumListener(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double accountBalance,Date subEndDate,int remainingSubDays) {
        super( userName,password,  fullName, email, phoneNumber, birthDate, accountBalance, subEndDate);
        this.remainingSubDays = remainingSubDays;
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
                ", remainingSubDays=" + remainingSubDays +
                '}';
    }
}

