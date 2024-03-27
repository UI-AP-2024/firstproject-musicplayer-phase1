package model.UserAccount;

import model.SubscriptionPlan;

import java.util.Date;

public class RegularListener extends Listener{
    final private int playListLimit;
    final private int addLimit;

    public RegularListener(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, SubscriptionPlan subscriptionPlan) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription, subscriptionPlan);
        playListLimit=3;//???????????
        addLimit=3;//??????????
    }

    public int getPlayListLimit() {
        return playListLimit;
    }

    public int getAddLimit() {
        return addLimit;
    }
}
