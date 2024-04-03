package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class PremiumListener extends Listener {
    private int remainDays;

    public PremiumListener(String userName, String password, String firstAndLastName, String email, String phoneNumber,
                           int year, int month, int day, long accountCredit, LocalDate finishTime, ArrayList<Genre> favoriteGenres
            , int remainDays) {
        super(userName, password, firstAndLastName, email, phoneNumber,
                year, month, day, accountCredit, favoriteGenres, finishTime);
        this.remainDays = remainDays;
    }

    public int getRemainDays() {
        return remainDays;
    }

    public void setRemainDays(int remainDays) {
        this.remainDays = remainDays;
    }
    public String toString(){
        return super.toString()+"\nRemain Days:"+getRemainDays()+"\nPremium Pack FinishDate:"+getFinishTime();
    }
}

