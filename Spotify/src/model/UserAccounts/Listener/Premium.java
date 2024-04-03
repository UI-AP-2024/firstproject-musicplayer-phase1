package model.UserAccounts.Listener;

import model.Genre.Genre;
import model.Playlist.Playlist;
import model.UserAccounts.Listener.Listener;
import java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;

public class Premium extends Listener {
    private Calendar calendar;
    private int leftOverDays;
    public Premium(String userId, String password, String fullName, String email, String phoneNumber, Date birthday, double credit, ArrayList<Playlist> playlists, ArrayList<Genre> favouriteGenres,int leftOverDays) {
        super(userId, password, fullName, email, phoneNumber,birthday,credit, playlists, favouriteGenres);
//        this.leftOverDays = leftOverDays;
//        calendar=Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.DAY_OF_YEAR,leftOverDays);
//        super.setDateOfEndSubscription(calendar.getTime());

    }

    public int getLeftOverDays() {
        return leftOverDays;
    }

    public void setLeftOverDays(int leftOverDays) {
        this.leftOverDays = leftOverDays;
        calendar=Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.DAY_OF_YEAR,leftOverDays);
        super.setDateOfEndSubscription(calendar.getTime());
    }
    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\n");
        context.append("left over days : ");
        context.append(leftOverDays);
        context.append("\n");
        return context.toString();
    }
}
