package model.Audio;

import model.SubscriptionPlan;
import model.UserAccount.User;

import java.util.Date;

public class Music extends User {
    private String caption;

    public Music(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, String caption) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
