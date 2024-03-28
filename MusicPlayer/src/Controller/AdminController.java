package Controller;
import Model.AdminModel;
import Model.ArtistModel;
import Model.Database;
import Model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class AdminController extends User{
    private AdminModel model ;
    private AdminController(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
    }
    public boolean enterUserPanel(String username, String password){
        return AdminModel.getAdmin().getUsername().equals(username) && AdminModel.getAdmin().getPassword().equals(password);
    }
    public String showPopularSongs(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
             if(Database.getDatabase().getAudios().get(i).getLikeCount() > 10)
                 show.append(Database.getDatabase().getAudios().get(i).getAudioName());
        }
        return show.toString();
    }
    public String showAudioInfo(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
            show.append("Audio Name: "+ Database.getDatabase().getAudios().get(i).getAudioName() + "\nArtist Name: " + Database.getDatabase().getAudios().get(i).getArtistName() + "\nAudio ID: "+Database.getDatabase().getAudios().get(i).getIDCount() +"\n");
        }
        return show.toString();
    }
    public String showArtistInfo(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++)
            if(Database.getDatabase().getUsers().getClass().equals(ArtistModel.class))
                show.append("Artist Name: "+Database.getDatabase().getUsers().get(i).getFullName()+ "\n");
        return show.toString();
    }
    public String showReports(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getReports().size();i++)
            show.append("User: "+Database.getDatabase().getReports().get(i).getUser() + "\nReported Artist: "+Database.getDatabase().getReports().get(i).getReportedArtist() + "\nDescription: "+ Database.getDatabase().getReports().get(i).getDescription() +"\n");
        return show.toString();
    }
    public String showUserInfo(){
        return "Username: "+ getUsername() + "\n" + "Full Name: " + getFullName() + "\nPhone Number: " + getPhoneNumber() + "\nEmail: " + getEmail() + "\nBirth: " + getBirthDate();
    }
}
