package Model.Accounts.Listeners;

import Model.Audios.Genre;

import java.util.ArrayList;
import java.util.Date;

public class PremiumListenerModel extends ListenerModel {
    private int premiumDayRemain;

    public PremiumListenerModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate) {
        super(username, password, fullName, email, phoneNumber, birthDate);
    }

    public int getPremiumDayRemain(){
        return this.premiumDayRemain;
    }

    public void setPremiumDayRemain(int days){
        this.premiumDayRemain = days;
    }
}
