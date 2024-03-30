package model.UserAccount.Listener;

import model.Genre;
import model.UserAccount.Listener.Listener;

import java.time.LocalDate;

public class Premium extends Listener
{
    private int remainingDays;
    public Premium(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth,Genre[] genres) {
        super(userName,passWord,name,email,phoneNumber,dateOfBirth,genres);
    }

    public int getRemainingDays() {
        return remainingDays;
    }

    public void setRemainingDays(int remainingDays) {
        this.remainingDays = remainingDays;
    }
    @Override
    public String toString()
    {
        return "UserName: " + getUserName()+"\t"+"PassWord: "+getPassWord()+"\t"+"Name: "+getName()+"\t"+"Email: "+getEmail()+"\t"+"PhoneNumber: "+getPhoneNumber()+"\t"+"Birth Date: "+getDateOfBirth()+"\t"+"Favorite Genres: "+getGenres();
    }
}
