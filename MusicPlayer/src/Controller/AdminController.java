package Controller;
import Model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class AdminController {
    public AdminController(){};
    public boolean enterUserPanel(String username, String password){
        return AdminModel.getAdmin().getUsername().equals(username) && AdminModel.getAdmin().getPassword().equals(password);
    }
    public String showPopularSongs(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
             if(Database.getDatabase().getAudios().get(i).getLikeCount() >=1)
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
    public String showOneAudioInfo(int ID){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getIDCount()==ID){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio name: "+audio.getAudioName()+"\tArtist name: "+audio.getArtistName() + "\n");
                break;
            }
        }
        return show.toString();
    }
    public String showArtistInfo(){
        StringBuilder show = new StringBuilder();
        for (User user : Database.getDatabase().getUsers())
            if(user instanceof SingerModel || user instanceof PodcasterModel)
                show.append("Artist Name: "+user.getFullName()+ "\n");
        return show.toString();
    }
    public String showOneArtistInfo(String userName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++)
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(userName)) {
                ArtistModel artist =(ArtistModel) Database.getDatabase().getUsers().get(i);
                show.append("Name: " + artist.getFullName()+"\temail:"+artist.getEmail()+"\tphone number: "+artist.getPhoneNumber()+"\tsalary: "+artist.getSalary()+"\nBiography"+artist.getBiography()+"\n");
                break;
            }
        return show.toString();
    }
    public String showReports(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getReports().size();i++)
            show.append("User: "+Database.getDatabase().getReports().get(i).getUser().getFullName() + "\nReported Artist: "+Database.getDatabase().getReports().get(i).getReportedArtist().getFullName() + "\nDescription: "+ Database.getDatabase().getReports().get(i).getDescription() +"\n");
        return show.toString();
    }
    public String showUserInfo(){
        return "Username: "+ AdminModel.getAdmin().getUsername() + "\n" + "Full Name: " + AdminModel.getAdmin().getFullName() + "\nPhone Number: " + AdminModel.getAdmin().getPhoneNumber() + "\nEmail: " + AdminModel.getAdmin().getEmail() + "\nBirth: " + AdminModel.getAdmin().getBirthDate();
    }
}
