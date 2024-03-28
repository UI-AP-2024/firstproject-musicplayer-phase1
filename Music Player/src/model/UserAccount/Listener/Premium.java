package model.UserAccount.Listener;

import model.Genre;
import model.UserAccount.Listener.Listener;

import java.time.LocalDate;

public class Premium extends Listener
{
    private int remainingDays;
    public Premium(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, Genre[] genres) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, genres);
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
}
