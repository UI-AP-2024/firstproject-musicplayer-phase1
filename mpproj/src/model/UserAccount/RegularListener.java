package model.UserAccount;

import model.SubscriptionPlan;

import java.util.Date;

public class RegularListener extends Listener{
    final private int playListLimit;
    final private int addLimit;

    public RegularListener(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth);
        playListLimit=3;//???????????
        addLimit=10;//??????????
    }

    public int getPlayListLimit() {
        return playListLimit;
    }

    public int getAddLimit() {
        return addLimit;
    }
}
