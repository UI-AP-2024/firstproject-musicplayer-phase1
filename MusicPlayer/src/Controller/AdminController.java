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
        sortingAudioFilesBasedOnLikes();
        for (AudioModel audio : Database.getDatabase().getAudios())
             show.append("Audio: ").append(audio.getAudioName()).append("\n");

        return show.toString();
    }

    private void sortingAudioFilesBasedOnLikes(){

        for (int i = 0; i < Database.getDatabase().getAudios().size() - 1; i++) {
            int likeBefore = Database.getDatabase().getAudios().get(i).getLikeCount();
            for (int j = i + 1; j < Database.getDatabase().getAudios().size(); j++) {
                int likeAfter = Database.getDatabase().getAudios().get(j).getLikeCount();
                if(likeAfter > likeBefore){
                    int temp = likeAfter;
                    likeAfter = likeBefore;
                    likeBefore = temp;
                }
            }

        }

    }
    public String showAudioInfo(){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios())
            show.append("Audio Name: "+ audio.getAudioName() + "\nArtist Name: " + audio.getArtistName() + "\nAudio ID: "+audio.getIDCount() +"\n");

        return show.toString();
    }
    public String showOneAudioInfo(int ID){
        StringBuilder show = new StringBuilder();
        for (AudioModel audio : Database.getDatabase().getAudios())
            if(audio.getIDCount()==ID){
                show.append("Audio name: "+audio.getAudioName()+"\tArtist name: "+audio.getArtistName() + "\n");
                break;
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
        for (User user : Database.getDatabase().getUsers())
            if(user.getUsername().equals(userName)) {
                ArtistModel artist =(ArtistModel) user;
                show.append("Name: " + artist.getFullName()+"\temail:"+artist.getEmail()+"\tphone number: "+artist.getPhoneNumber()+"\tsalary: "+artist.getSalary()+"\nBiography"+artist.getBiography()+"\n");
                break;
            }

        return show.toString();
    }
    public String showReports(){
        StringBuilder show = new StringBuilder();
        for (Report reports : Database.getDatabase().getReports())
            show.append("User: "+reports.getUser().getFullName() + "\nReported Artist: "+reports.getReportedArtist().getFullName() + "\nDescription: "+ reports.getDescription() +"\n");
        return show.toString();
    }
    public String showUserInfo(){
        return "Username: "+ AdminModel.getAdmin().getUsername() + "\n" + "Full Name: " + AdminModel.getAdmin().getFullName() + "\nPhone Number: " + AdminModel.getAdmin().getPhoneNumber() + "\nEmail: " + AdminModel.getAdmin().getEmail() + "\nBirth: " + AdminModel.getAdmin().getBirthDate();
    }
}
