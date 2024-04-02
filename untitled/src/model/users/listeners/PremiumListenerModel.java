package model.users.listeners;

import java.util.Calendar;

public class PremiumListenerModel extends ListenerModel
{
    private int remainingDays;
    public PremiumListenerModel(String userName, String password, String fullName, String email, String phoneNumber, String birthDate)
    {
        super(userName,password,fullName,email,phoneNumber,birthDate);
    }
    public void setRemainingDays(int remainingDays)
    {
        this.remainingDays=remainingDays;
    }
    public int getRemainingDays()
    {
        return this.remainingDays;
    }
    public String toString()
    {
        return super.toString()+"Remaining subscription days: "+(--this.remainingDays);
    }
}
