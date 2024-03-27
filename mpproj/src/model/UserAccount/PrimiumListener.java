package model.UserAccount;

import model.SubscriptionPlan;

import java.util.Date;

public class PrimiumListener extends Listener {
    private int numOfSub;

    public PrimiumListener(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, SubscriptionPlan subscriptionPlan) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription, subscriptionPlan);
    }

    public void setNumOfSub(int numOfSub) {
        this.numOfSub = numOfSub;
    }

    public int getNumOfSub() {
        return numOfSub;
    }
}
