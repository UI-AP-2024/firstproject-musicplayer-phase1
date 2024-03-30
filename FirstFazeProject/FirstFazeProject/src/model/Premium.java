package model;

import java.util.Date;

public class Premium extends Listener{
    private int shareDaysLeft;

    public Premium(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate);
    }

    public int getShareDaysLeft() {
        return shareDaysLeft;
    }

    public void setShareDaysLeft(int shareDaysLeft) {
        this.shareDaysLeft = shareDaysLeft;
    }
}
