package controller;

import model.Audio.Audio;
import model.Database;
import model.Report;
import model.UserAccount.Admin;
import model.UserAccount.Artist.Artist;
import model.UserAccount.Listener.Listener;
import model.UserAccount.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;

public class Admin2 extends UserAccount2
{
    private Admin2 admin2;

    public Admin2 getAdmin2() {
        return admin2;
    }

    public void setAdmin2(Admin2 admin2) {
        this.admin2 = admin2;
    }

    private Admin admin;
    public Admin newAdmin(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth)
    {
        admin = newAdmin(userName,passWord,name,email,phoneNumber,dateOfBirth);
        Database.getDatabase().getUserAccounts().add(admin);
        return admin;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    Artist artist;
    ArrayList<Artist> artists = new ArrayList<Artist>();
    public void addArtist(Artist artist)
    {
        artists.add(artist);
    }
    public String artistInfo()
    {
        for(Artist a:artists)
        {
            return a.toString()+"\n";
        }
        return null;
    }
    public void addAudio(Audio audio)
    {
        Database.getDatabase().getAudio().add(audio);
    }
    public String audioInfo()
    {
        for(int i=0;i<Database.getDatabase().getAudio().size(); i++)
        {
            return Database.getDatabase().getAudio().get(i).toString()+"\n";
        }
        return null;
    }
    public void addReport(Report report)
    {
        Database.getDatabase().getReports().add(report);
    }
    public String reportInfo()
    {
        for(int i=0; i<Database.getDatabase().getReports().size(); i++)
        {
            return Database.getDatabase().getReports().get(i).toString() + "/n";
        }
        return null;
    }
    UserAccount userAccount;
    public void addUserAccount(UserAccount userAccount)
    {
        Database.getDatabase().getUserAccounts().add(userAccount);
    }
    public String userInfo()
    {
        for(int i=0; i<Database.getDatabase().getUserAccounts().size(); i++)
        {
            return Database.getDatabase().getUserAccounts().get(i).toString() + "\n";
        }
        return null;
    }


}
